package io.github.sefiraat.slimetinker.i18n;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.sefiraat.slimetinker.compat.Pdc;
import io.github.sefiraat.slimetinker.items.tinkermaterials.TinkerMaterial;
import io.github.sefiraat.slimetinker.items.tinkermaterials.TinkerMaterialManager;
import io.github.sefiraat.slimetinker.utils.ItemUtils;
import io.github.sefiraat.slimetinker.utils.Keys;
import io.github.sefiraat.slimetinker.utils.ThemeUtils;
import io.github.thebusybiscuit.slimefun5.core.services.localization.ItemTextResolver;
import io.github.thebusybiscuit.slimefun5.core.services.localization.ItemTranslationService.RenderedDisplay;

import net.md_5.bungee.api.ChatColor;

/**
 * Resolves SlimeTinker's assembled tools/armour/parts for Slimefun's per-viewer packet translation.
 * Their name is composed at runtime from the material + type ids stored in the stack's PDC (and tools
 * /armour rebuild their whole lore per language), so they can't live in a static {@code items.yml};
 * this is the item-aware resolver that replaces the old {@code TinkerTranslationListener}. Because a
 * tinker item's id (e.g. {@code TOOL_PICKAXE}) also backs a static guide entry, the resolver only
 * claims the ASSEMBLED instances (identified by their tinker PDC) and returns {@code null} otherwise,
 * leaving the guide item to its static entry.
 *
 * <p>
 * For an English viewer (or a missing translation) the rebuilt name is byte-identical to the original
 * composition. Player renames are preserved by the core packet layer (it skips the display name of a
 * {@code RenamedItems}-marked stack); only the lore is refreshed.
 */
public final class TinkerItemResolver implements ItemTextResolver {

    // (PART_CLASS[, PART_TYPE]) -> the part's English display word, mirroring the Parts templates.
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

    @Override
    @Nullable
    public RenderedDisplay resolve(@Nullable ItemStack item, String itemId, @Nullable String languageId) {
        if (item == null) {
            return null; // per-instance: needs the actual stack's PDC
        }

        try {
            boolean tool = ItemUtils.isTool(item);
            boolean armour = !tool && ItemUtils.isArmour(item);
            boolean part = !tool && !armour && ItemUtils.getPartClass(item) != null;

            if (!tool && !armour && !part) {
                return null; // not an assembled tinker item (e.g. the static guide item)
            }

            ItemMeta meta = item.getItemMeta();

            if (meta == null) {
                return null;
            }

            String name = tool ? composeTool(meta, languageId)
                : armour ? composeArmour(meta, languageId)
                : composePart(meta, languageId);

            if (name == null) {
                return null;
            }

            List<String> lore;

            if (tool) {
                lore = ItemUtils.buildToolLore(meta, item, languageId);
            } else if (armour) {
                lore = ItemUtils.buildArmourLore(meta, item, languageId);
            } else {
                // Parts keep their static lore (mirrors the old listener); reuse whatever the stack carries.
                lore = meta.getLore() != null ? meta.getLore() : Collections.<String>emptyList();
            }

            return RenderedDisplay.of(name, lore);
        } catch (Exception | LinkageError ignored) {
            // A broken composition must not break packet rendering for the item.
            return null;
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
        String material = Pdc.getString(meta, Keys.PART_MATERIAL.toString());
        String partClass = Pdc.getString(meta, Keys.PART_CLASS.toString());
        String partType = Pdc.getString(meta, Keys.PART_TYPE.toString());

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
}
