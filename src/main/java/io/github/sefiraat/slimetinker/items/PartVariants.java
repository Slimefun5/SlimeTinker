package io.github.sefiraat.slimetinker.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.inventory.ItemStack;

import io.github.sefiraat.slimetinker.SlimeTinker;
import io.github.sefiraat.slimetinker.itemgroups.ItemGroups;
import io.github.sefiraat.slimetinker.items.templates.PartTemplate;
import io.github.sefiraat.slimetinker.items.tinkermaterials.TinkerMaterial;
import io.github.sefiraat.slimetinker.items.tinkermaterials.TinkerMaterialManager;
import io.github.sefiraat.slimetinker.items.workstations.smeltery.DummySmeltery;
import io.github.sefiraat.slimetinker.managers.TraitManager;
import io.github.sefiraat.slimetinker.utils.Ids;
import io.github.sefiraat.slimetinker.utils.ItemUtils;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.core.guide.variants.VariantGroup;
import io.github.thebusybiscuit.slimefun5.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey;

/**
 * Registers every part-and-material combination as a real item and groups the combinations of one part
 * shape into a single guide slot.
 *
 * @implNote Replaces the twelve inert {@code PART_*_DUMMY} guide entries. Those advertised a part shape but
 *           could not be used for anything, and the usable per-material parts existed only as unregistered
 *           runtime clones, so the guide could show a shape OR a working part but never both. Every variant
 *           is now registered, and core's {@link VariantGroup} collapses a shape's variants back into one
 *           cycling slot so the guide does not grow by a thousand tiles.
 *           <p>
 *           Must run after {@code TinkerMaterialManager}: which materials support which parts depends on
 *           their traits and on the trait config, which only exists once that manager has been built.
 */
public final class PartVariants {

    private static final java.util.concurrent.atomic.AtomicBoolean DIAG = new java.util.concurrent.atomic.AtomicBoolean(false);

    private PartVariants() {
        throw new UnsupportedOperationException("Utility Class");
    }

    /** One part shape, and the material trait that decides whether a material can be made into it. */
    private enum Shape {
        SHOVEL_HEAD(Parts.SHOVEL_HEAD, "SHOVEL_HEAD", Ids.HEAD, Ids.SHOVEL),
        PICKAXE_HEAD(Parts.PICKAXE_HEAD, "PICKAXE_HEAD", Ids.HEAD, Ids.PICKAXE),
        AXE_HEAD(Parts.AXE_HEAD, "AXE_HEAD", Ids.HEAD, Ids.AXE),
        HOE_HEAD(Parts.HOE_HEAD, "HOE_HEAD", Ids.HEAD, Ids.HOE),
        SWORD_BLADE(Parts.SWORD_BLADE, "SWORD_BLADE", Ids.HEAD, Ids.SWORD),
        TOOL_ROD(Parts.TOOL_ROD, "TOOL_ROD", Ids.ROD, null),
        MAIL_LINKS(Parts.MAIL_LINKS, "MAIL_LINKS", Ids.LINKS, null),
        HELM_PLATE(Parts.HELM_PLATE, "HELM_PLATE", Ids.PLATE, Ids.HELMET),
        CHEST_PLATE(Parts.CHEST_PLATE, "CHEST_PLATE", Ids.PLATE, Ids.CHESTPLATE),
        LEG_PLATE(Parts.LEG_PLATE, "LEG_PLATE", Ids.PLATE, Ids.LEGGINGS),
        BOOT_PLATE(Parts.BOOT_PLATE, "BOOT_PLATE", Ids.PLATE, Ids.BOOTS);

        private final PartTemplate template;
        private final String idPart;
        private final String partClass;
        private final String partType;

        Shape(PartTemplate template, String idPart, String partClass, @Nullable String partType) {
            this.template = template;
            this.idPart = idPart;
            this.partClass = partClass;
            this.partType = partType;
        }

        /**
         * Whether {@code material} can be made into this shape - the same trait-plus-config test
         * {@code TinkerMaterialManager} uses when it builds the casting results, so the guide can never
         * advertise a variant the smeltery refuses to produce.
         */
        boolean supports(@Nonnull String materialId, @Nonnull TinkerMaterial material, @Nonnull TraitManager traits) {
            if (!traits.isEnabled(materialId, partClass)) {
                return false;
            }

            if (Ids.ROD.equals(partClass)) {
                return material.getTraitToolRod() != null;
            }

            if (Ids.LINKS.equals(partClass)) {
                return material.getTraitArmorLinks() != null;
            }

            if (Ids.HEAD.equals(partClass)) {
                return material.getTraitToolHead() != null;
            }

            return material.getTraitArmorPlates() != null;
        }
    }

    /**
     * Registers a variant per (shape, material) and one {@link VariantGroup} per shape.
     *
     * @param plugin
     *            The addon registering the items
     */
    public static void set(@Nonnull SlimeTinker plugin) {
        TraitManager traits = plugin.getTraitManager();
        Map<String, TinkerMaterial> materials = TinkerMaterialManager.getMap();

        for (Shape shape : Shape.values()) {
            List<SlimefunItem> variants = new ArrayList<>();

            for (Map.Entry<String, TinkerMaterial> entry : materials.entrySet()) {
                String materialId = entry.getKey();
                TinkerMaterial material = entry.getValue();

                if (!shape.supports(materialId, material, traits)) {
                    continue;
                }

                variants.add(registerVariant(plugin, shape, materialId, material));
            }

            if (!variants.isEmpty()) {
                new VariantGroup(new NamespacedKey(plugin, "part_" + shape.idPart.toLowerCase()), variants).register();
            }
        }
    }

    @Nonnull
    private static SlimefunItem registerVariant(@Nonnull SlimeTinker plugin, @Nonnull Shape shape,
            @Nonnull String materialId, @Nonnull TinkerMaterial material) {
        // The stack already carries the part identity the workstations read; wrapping it in a
        // SlimefunItemStack only adds the item id, so a variant IS a usable part straight from the guide.
        ItemStack stack = shape.template.getStack(materialId, shape.partClass, shape.partType, material.getColor());
        SlimefunItemStack item = new SlimefunItemStack("PART_" + shape.idPart + "_" + materialId, stack);

        UnplaceableBlock variant = new UnplaceableBlock(ItemGroups.PARTS, item, DummySmeltery.TYPE,
            ItemUtils.getMiddleOnlyRecipe(material.getRepresentativeStack()));
        variant.register(plugin);

        // TEMPORARY diagnostic
        if (DIAG.compareAndSet(false, true)) {
            org.bukkit.inventory.ItemStack t = variant.getItem();
            plugin.getLogger().warning("[diag] " + item.getItemId()
                + " partClass=" + io.github.sefiraat.slimetinker.utils.ItemUtils.getPartClass(t)
                + " material=" + io.github.sefiraat.slimetinker.utils.ItemUtils.getPartMaterial(t)
                + " isTool=" + io.github.sefiraat.slimetinker.utils.ItemUtils.isTool(t)
                + " resolved=" + new io.github.sefiraat.slimetinker.i18n.TinkerItemResolver().resolve(t, item.getItemId(), "en"));
        }

        return variant;
    }
}
