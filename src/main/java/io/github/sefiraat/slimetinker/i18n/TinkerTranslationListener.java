package io.github.sefiraat.slimetinker.i18n;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.sefiraat.slimetinker.SlimeTinker;
import io.github.sefiraat.slimetinker.compat.Pdc;
import io.github.sefiraat.slimetinker.items.tinkermaterials.TinkerMaterial;
import io.github.sefiraat.slimetinker.items.tinkermaterials.TinkerMaterialManager;
import io.github.sefiraat.slimetinker.utils.ItemUtils;
import io.github.sefiraat.slimetinker.utils.Keys;
import io.github.sefiraat.slimetinker.utils.ThemeUtils;
import io.github.thebusybiscuit.slimefun5.api.events.PlayerLanguageChangeEvent;
import io.github.thebusybiscuit.slimefun5.core.services.localization.Language;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;

import net.md_5.bungee.api.ChatColor;

/**
 * Re-skins SlimeTinker tools and armour into the holding player's language. Their names are composed
 * at creation from material + type ids stored in the item's PDC, so this reproduces that exact
 * composition - swapping {@code toTitleCase(id)} for a {@link TinkerLang} lookup that falls back to the
 * same title-cased form. For an English viewer (or a missing translation) the rebuilt name is
 * byte-identical to the original, so nothing changes; only a translated holder sees a translated name.
 *
 * A renamed item (its name matches neither the English composition nor a previous translation of ours)
 * is left untouched. Items we have translated carry a PDC marker so they can be switched between
 * languages later.
 */
public class TinkerTranslationListener implements Listener {

    // (PART_CLASS[, PART_TYPE]) -> the part's English display word, mirroring the Parts templates.
    // Key is PART_CLASS, or PART_CLASS + "_" + PART_TYPE when a type is set. Used as the English
    // baseline and as the tinker.yml "part-types" lookup key.
    private static final java.util.Map<String, String> PART_NAMES = new java.util.HashMap<>();

    static {
        PART_NAMES.put("HEAD_SHOVEL", "Shovel Head");
        PART_NAMES.put("HEAD_PICK", "Pickaxe Head");
        PART_NAMES.put("HEAD_AXE", "Axe Head");
        PART_NAMES.put("HEAD_HOE", "Hoe Head");
        PART_NAMES.put("HEAD_SWORD", "Sword Blade");
        PART_NAMES.put("ROD", "Tool Rod");
        PART_NAMES.put("BINDING", "Binding");
        PART_NAMES.put("PLATE_HELMET", "Helmet Plates");
        PART_NAMES.put("PLATE_CHESTPLATE", "Chestplate Plates");
        PART_NAMES.put("PLATE_LEGGINGS", "Legging Plates");
        PART_NAMES.put("PLATE_BOOTS", "Boot Plates");
        PART_NAMES.put("GAMBESON", "Gambeson");
        PART_NAMES.put("LINKS", "Mail Links");
        PART_NAMES.put("REPAIR", "Repair Kit");
    }

    public TinkerTranslationListener(@Nonnull SlimeTinker plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        // EntityPickupItemEvent (MC 1.12+) lives in its own guarded listener so this listener still
        // registers its PlayerLanguageChangeEvent handler on 1.8-1.11.
        try {
            Class.forName("org.bukkit.event.entity.EntityPickupItemEvent");
            plugin.getServer().getPluginManager().registerEvents(new TinkerPickupListener(this), plugin);
        } catch (ClassNotFoundException ignored) {
            // 1.8-1.11: no EntityPickupItemEvent, the periodic sweep still re-skins held items.
        }

        // Periodic sweep so items added by any means end up in the holder's language. Only rewrites a
        // stack when its name is not already correct, so stable inventories incur no changes.
        plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
            for (Player p : plugin.getServer().getOnlinePlayers()) {
                sweep(p);
            }
        }, 120L, 80L);
    }

    @EventHandler
    public void onLanguageChange(@Nonnull PlayerLanguageChangeEvent e) {
        Player p = e.getPlayer();
        // The language is applied after this event resolves, so re-skin one tick later.
        SlimeTinker.getInstance().getServer().getScheduler().runTask(SlimeTinker.getInstance(), () -> sweep(p));
    }

    private void sweep(@Nonnull Player p) {
        ItemStack[] contents = p.getInventory().getContents();

        for (int slot = 0; slot < contents.length; slot++) {
            if (apply(p, contents[slot])) {
                p.getInventory().setItem(slot, contents[slot]);
            }
        }
    }

    /** Rebuilds a tinker tool/armour name into the player's language. Returns whether the stack changed. */
    public boolean apply(@Nonnull Player p, @Nullable ItemStack stack) {
        if (stack == null) {
            return false;
        }

        try {
            boolean tool = ItemUtils.isTool(stack);
            boolean armour = !tool && ItemUtils.isArmour(stack);
            boolean part = !tool && !armour && ItemUtils.getPartClass(stack) != null;

            if (!tool && !armour && !part) {
                return false;
            }

            ItemMeta meta = stack.getItemMeta();

            if (meta == null) {
                return false;
            }

            String language = languageOf(p);
            String target = tool ? composeTool(meta, language) : armour ? composeArmour(meta, language) : composePart(meta, language);
            String english = tool ? composeTool(meta, null) : armour ? composeArmour(meta, null) : composePart(meta, null);

            if (target == null || english == null) {
                return false;
            }

            String current = meta.hasDisplayName() ? meta.getDisplayName() : null;
            boolean managed = "Y".equals(Pdc.getString(meta, Keys.TOOL_I18N_MANAGED.toString()));

            // Only touch an item still showing the default English composition or one we previously
            // translated - never a name a player set themselves (e.g. via an anvil).
            if (!managed && !english.equals(current)) {
                return false;
            }

            boolean changed = false;

            if (!target.equals(current)) {
                meta.setDisplayName(target);
                changed = true;
            }

            // Tools and armour also get their lore rebuilt in the holder's language (labels, traits,
            // modifier names). Parts keep their static lore for now.
            if (tool || armour) {
                List<String> targetLore = tool
                    ? ItemUtils.buildToolLore(meta, stack, language)
                    : ItemUtils.buildArmourLore(meta, stack, language);

                if (!targetLore.equals(meta.getLore())) {
                    meta.setLore(targetLore);
                    changed = true;
                }
            }

            if (changed) {
                Pdc.setString(meta, Keys.TOOL_I18N_MANAGED.toString(), "Y");
                stack.setItemMeta(meta);
                return true;
            }

            return false;
        } catch (Exception | LinkageError ignored) {
            return false;
        }
    }

    @Nonnull
    private String composeTool(@Nonnull ItemMeta meta, @Nullable String language) {
        String head = ItemUtils.getToolHeadMaterial(meta);
        String binder = ItemUtils.getToolBindingMaterial(meta);
        String rod = ItemUtils.getToolRodMaterial(meta);
        String type = ItemUtils.getToolTypeName(meta);

        return color(head) + TinkerLang.translate("materials", head, language)
            + "-" + color(binder) + TinkerLang.translate("materials", binder, language)
            + "-" + color(rod) + TinkerLang.translate("materials", rod, language)
            + " " + ChatColor.WHITE + TinkerLang.translate("tool-types", type, language);
    }

    @Nonnull
    private String composeArmour(@Nonnull ItemMeta meta, @Nullable String language) {
        String plate = ItemUtils.getArmourPlateMaterial(meta);
        String gambeson = ItemUtils.getArmourGambesonMaterial(meta);
        String links = ItemUtils.getArmourLinksMaterial(meta);
        String type = ItemUtils.getArmourTypeName(meta);

        return color(plate) + TinkerLang.translate("materials", plate, language)
            + "-" + color(gambeson) + TinkerLang.translate("materials", gambeson, language)
            + "-" + color(links) + TinkerLang.translate("materials", links, language)
            + " " + ChatColor.WHITE + TinkerLang.translate("armour-types", type, language);
    }

    @Nullable
    private String composePart(@Nonnull ItemMeta meta, @Nullable String language) {
        String material = io.github.sefiraat.slimetinker.compat.Pdc.getString(meta, Keys.PART_MATERIAL.toString());
        String partClass = io.github.sefiraat.slimetinker.compat.Pdc.getString(meta, Keys.PART_CLASS.toString());
        String partType = io.github.sefiraat.slimetinker.compat.Pdc.getString(meta, Keys.PART_TYPE.toString());

        if (material == null || partClass == null) {
            return null;
        }

        String key = partType != null ? partClass + "_" + partType : partClass;
        String english = PART_NAMES.get(key);

        if (english == null) {
            return null; // Unknown part - leave it untouched.
        }

        String partName = TinkerLang.lookup("part-types", key, language);

        if (partName == null) {
            partName = english;
        }

        return color(material) + TinkerLang.translate("materials", material, language)
            + ThemeUtils.ITEM_PART + " " + partName;
    }

    @Nonnull
    private String color(@Nullable String materialId) {
        if (materialId == null) {
            return "";
        }

        TinkerMaterial material = TinkerMaterialManager.getById(materialId);
        return material == null ? "" : material.getColor().toString();
    }

    @Nullable
    private String languageOf(@Nonnull Player p) {
        Language language = Slimefun.getLocalization().getLanguage(p);
        return language != null ? language.getId() : null;
    }
}
