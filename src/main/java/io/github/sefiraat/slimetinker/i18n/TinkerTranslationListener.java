package io.github.sefiraat.slimetinker.i18n;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.sefiraat.slimetinker.SlimeTinker;
import io.github.sefiraat.slimetinker.compat.Pdc;
import io.github.sefiraat.slimetinker.items.tinkermaterials.TinkerMaterial;
import io.github.sefiraat.slimetinker.items.tinkermaterials.TinkerMaterialManager;
import io.github.sefiraat.slimetinker.utils.ItemUtils;
import io.github.sefiraat.slimetinker.utils.Keys;
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

    public TinkerTranslationListener(@Nonnull SlimeTinker plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        // Periodic sweep so items added by any means end up in the holder's language. Only rewrites a
        // stack when its name is not already correct, so stable inventories incur no changes.
        plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
            for (Player p : plugin.getServer().getOnlinePlayers()) {
                sweep(p);
            }
        }, 120L, 80L);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPickup(@Nonnull EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            ItemStack stack = e.getItem().getItemStack();

            if (apply(p, stack)) {
                e.getItem().setItemStack(stack);
            }
        }
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

            if (!tool && !armour) {
                return false;
            }

            ItemMeta meta = stack.getItemMeta();

            if (meta == null) {
                return false;
            }

            String language = languageOf(p);
            String target = tool ? composeTool(meta, language) : composeArmour(meta, language);
            String current = meta.hasDisplayName() ? meta.getDisplayName() : null;

            if (target.equals(current)) {
                return false;
            }

            String english = tool ? composeTool(meta, null) : composeArmour(meta, null);
            boolean managed = "Y".equals(Pdc.getString(meta, Keys.TOOL_I18N_MANAGED.toString()));

            // Only rewrite an item that is still showing the default English composition or one we
            // previously translated - never a name a player set themselves (e.g. via an anvil).
            if (managed || english.equals(current)) {
                meta.setDisplayName(target);
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
