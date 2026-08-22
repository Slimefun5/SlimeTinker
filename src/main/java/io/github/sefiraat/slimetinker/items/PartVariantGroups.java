package io.github.sefiraat.slimetinker.items;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import io.github.sefiraat.slimetinker.SlimeTinker;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.core.guide.variants.VariantGroup;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey;

/**
 * Collapses this addon's per-material part items into one guide slot per part shape.
 *
 * @implNote Groups the items {@code TinkerMaterial} ALREADY registers - {@code PART_HEAD_PICKIRON},
 *           {@code PART_ROD_IRON}, {@code PART_PLATES_CHESTPLATEIRON} and so on, 700-odd of them in
 *           {@code ItemGroups.PART_DICT}. An earlier attempt registered a second, parallel set of
 *           per-material parts and grouped only those, which doubled the addon's item count while leaving
 *           the originals listed one tile each - the pages the player actually sees. Nothing is registered
 *           here; this only groups what exists.
 *           <p>
 *           Must run after the materials are built, since a material only registers the parts its traits
 *           and the trait config allow.
 */
public final class PartVariantGroups {

    /** Part shape -> the id prefix its per-material items share. Order is irrelevant: prefixes are distinct. */
    private static final Map<String, String> SHAPES = new LinkedHashMap<>();

    static {
        SHAPES.put("sword_blade", "PART_HEAD_SWORD");
        SHAPES.put("hoe_head", "PART_HEAD_HOE");
        SHAPES.put("axe_head", "PART_HEAD_AXE");
        SHAPES.put("pickaxe_head", "PART_HEAD_PICK");
        SHAPES.put("shovel_head", "PART_HEAD_SHOVEL");
        SHAPES.put("tool_rod", "PART_ROD_");
        SHAPES.put("binding", "PART_BINDING_");
        SHAPES.put("helmet_plates", "PART_PLATES_HELMET");
        SHAPES.put("chestplate_plates", "PART_PLATES_CHESTPLATE");
        SHAPES.put("legging_plates", "PART_PLATES_LEGGINGS");
        SHAPES.put("boot_plates", "PART_PLATES_BOOTS");
        SHAPES.put("gambeson", "PART_GAMBESON_");
        SHAPES.put("mail_links", "PART_LINKS_");
        SHAPES.put("repair_kit", "PART_REPAIR_KIT_");
    }

    private PartVariantGroups() {
        throw new UnsupportedOperationException("Utility Class");
    }

    /**
     * Registers one {@link VariantGroup} per part shape over the already-registered per-material parts.
     *
     * @param plugin
     *            The addon the group keys belong to
     */
    public static void set(@Nonnull SlimeTinker plugin) {
        Map<String, List<SlimefunItem>> byShape = new LinkedHashMap<>();

        for (SlimefunItem item : Slimefun.getRegistry().getEnabledSlimefunItems()) {
            String shape = shapeOf(item.getId());

            if (shape != null) {
                byShape.computeIfAbsent(shape, k -> new ArrayList<>()).add(item);
            }
        }

        for (Map.Entry<String, List<SlimefunItem>> entry : byShape.entrySet()) {
            // A single-member shape is left alone: a group of one saves no slot and only adds a counter.
            if (entry.getValue().size() > 1) {
                new VariantGroup(new NamespacedKey(plugin, "part_" + entry.getKey()), entry.getValue()).register();
            }
        }
    }

    /**
     * The shape {@code itemId} belongs to, or null if it is not a per-material part.
     *
     * @implNote The guide-only {@code *_DUMMY} entries and the shape templates must NOT be grouped: the
     *           dummies are the recipe-book placeholders and a template is not obtainable, so folding
     *           either into a group would hide a tile the player is meant to see.
     */
    private static String shapeOf(@Nonnull String itemId) {
        if (itemId.endsWith("_DUMMY")) {
            return null;
        }

        for (Map.Entry<String, String> shape : SHAPES.entrySet()) {
            String prefix = shape.getValue();

            // A longer id than the prefix means the remainder is the material name.
            if (itemId.startsWith(prefix) && itemId.length() > prefix.length()) {
                return shape.getKey();
            }
        }

        return null;
    }
}
