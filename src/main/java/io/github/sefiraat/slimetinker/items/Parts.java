package io.github.sefiraat.slimetinker.items;

import io.github.sefiraat.slimetinker.SlimeTinker;
import io.github.sefiraat.slimetinker.itemgroups.ItemGroups;
import io.github.sefiraat.slimetinker.items.templates.PartTemplate;
import io.github.sefiraat.slimetinker.items.templates.RepairkitTemplate;
import io.github.sefiraat.slimetinker.items.workstations.smeltery.DummySmeltery;
import io.github.sefiraat.slimetinker.items.workstations.workbench.DummyWorkbench;
import io.github.sefiraat.slimetinker.utils.ItemUtils;
import io.github.sefiraat.slimetinker.utils.SkullTextures;
import io.github.sefiraat.slimetinker.utils.ThemeUtils;
import io.github.sefiraat.slimetinker.utils.enums.ThemeItemType;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.implementation.items.blocks.UnplaceableBlock;
import io.github.sefiraat.slimetinker.utils.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import org.bukkit.inventory.ItemStack;

public final class Parts {

    private Parts() {
        throw new UnsupportedOperationException("Utility Class");
    }

    public static final String DESC_ERROR = "Error";

    public static final SlimefunItemStack PART_SHOVEL_HEAD_DUMMY = new BaseItem("PART_SHOVEL_HEAD_DUMMY", SkullTextures.PART_SHOVEL_HEAD);

    public static final SlimefunItemStack PART_PICKAXE_HEAD_DUMMY = new BaseItem("PART_PICKAXE_HEAD_DUMMY", SkullTextures.PART_PICKAXE_HEAD);

    public static final SlimefunItemStack PART_AXE_HEAD_DUMMY = new BaseItem("PART_AXE_HEAD_DUMMY", SkullTextures.PART_AXE_HEAD);

    public static final SlimefunItemStack PART_HOE_HEAD_DUMMY = new BaseItem("PART_HOE_HEAD_DUMMY", SkullTextures.PART_HOE_HEAD);

    public static final SlimefunItemStack PART_SWORD_BLADE_DUMMY = new BaseItem("PART_SWORD_BLADE_DUMMY", SkullTextures.PART_SWORD_BLADE);

    public static final SlimefunItemStack PART_TOOL_ROD_DUMMY = new BaseItem("PART_TOOL_ROD_DUMMY", SkullTextures.PART_TOOL_ROD);

    public static final SlimefunItemStack PART_BINDING_DUMMY = new BaseItem("PART_BINDING_DUMMY", SkullTextures.PART_BINDING);

    public static final SlimefunItemStack PART_HELM_PLATE_DUMMY = new BaseItem("PART_HELM_PLATE_DUMMY", SkullTextures.PART_HELM_PLATES);

    public static final SlimefunItemStack PART_CHEST_PLATE_DUMMY = new BaseItem("PART_CHEST_PLATE_DUMMY", SkullTextures.PART_CHEST_PLATES);

    public static final SlimefunItemStack PART_LEG_PLATE_DUMMY = new BaseItem("PART_LEG_PLATE_DUMMY", SkullTextures.PART_LEG_PLATES);

    public static final SlimefunItemStack PART_BOOT_PLATE_DUMMY = new BaseItem("PART_BOOT_PLATE_DUMMY", SkullTextures.PART_BOOTS_PLATES);

    public static final SlimefunItemStack PART_MAIL_LINKS_DUMMY = new BaseItem("PART_MAIL_LINKS_DUMMY", SkullTextures.PART_LINKS);

    public static final SlimefunItemStack PART_GAMBESON_DUMMY = new BaseItem("PART_GAMBESON_DUMMY", SkullTextures.PART_GAMBESON);

    public static final SlimefunItemStack PART_REPAIR_KIT_DUMMY = new BaseItem("PART_REPAIR_KIT_DUMMY", MaterialCompat.safe(XMaterial.CHEST_MINECART));

    public static final SlimefunItemStack PART_AXE_HEAD = new BaseItem("PART_AXE_HEAD", SkullTextures.PART_AXE_HEAD);

    public static final SlimefunItemStack PART_HOE_HEAD = new BaseItem("PART_HOE_HEAD", SkullTextures.PART_HOE_HEAD);

    public static final SlimefunItemStack PART_PICKAXE_HEAD = new BaseItem("PART_PICKAXE_HEAD", SkullTextures.PART_PICKAXE_HEAD);

    public static final SlimefunItemStack PART_SHOVEL_HEAD = new BaseItem("PART_SHOVEL_HEAD", SkullTextures.PART_SHOVEL_HEAD);

    public static final SlimefunItemStack PART_SWORD_BLADE = new BaseItem("PART_SWORD_BLADE", SkullTextures.PART_SWORD_BLADE);

    public static final SlimefunItemStack PART_TOOL_ROD = new BaseItem("PART_TOOL_ROD", SkullTextures.PART_TOOL_ROD);

    public static final SlimefunItemStack PART_REPAIR_KIT = new BaseItem("PART_REPAIR_KIT", MaterialCompat.safe(XMaterial.CHEST_MINECART));

    public static final SlimefunItemStack PART_BINDING_GENERAL_DISPLAY = new BaseItem("PART_BINDING_GENERAL_DISPLAY", MaterialCompat.safe(XMaterial.STRING));

    public static final SlimefunItemStack PART_HELM_PLATES = new BaseItem("PART_HELM_PLATES", SkullTextures.PART_HELM_PLATES);

    public static final SlimefunItemStack PART_CHEST_PLATES = new BaseItem("PART_CHEST_PLATES", SkullTextures.PART_CHEST_PLATES);

    public static final SlimefunItemStack PART_LEG_PLATES = new BaseItem("PART_LEG_PLATES", SkullTextures.PART_LEG_PLATES);

    public static final SlimefunItemStack PART_BOOTS_PLATES = new BaseItem("PART_BOOTS_PLATES", SkullTextures.PART_BOOTS_PLATES);

    public static final SlimefunItemStack PART_MAIL_LINKS = ThemeUtils.themedItemStack(
        "PART_MAIL_LINKS",
        SkullTextures.PART_LINKS,
        ThemeItemType.PART,
        DESC_ERROR,
        ThemeUtils.PASSIVE + DESC_ERROR
    );

    public static final SlimefunItemStack PART_GAMBESON_GENERAL_DISPLAY = new BaseItem("PART_GAMBESON_GENERAL_DISPLAY", MaterialCompat.safe(XMaterial.STRING));

    // Statics for Recipes
    public static final PartTemplate SHOVEL_HEAD = new PartTemplate(ItemGroups.DUMMY, PART_SHOVEL_HEAD, DummySmeltery.TYPE, new ItemStack[9], "Shovel Head");
    public static final PartTemplate PICKAXE_HEAD = new PartTemplate(ItemGroups.DUMMY, PART_PICKAXE_HEAD, DummySmeltery.TYPE, new ItemStack[9], "Pickaxe Head");
    public static final PartTemplate AXE_HEAD = new PartTemplate(ItemGroups.DUMMY, PART_AXE_HEAD, DummySmeltery.TYPE, new ItemStack[9], "Axe Head");
    public static final PartTemplate HOE_HEAD = new PartTemplate(ItemGroups.DUMMY, PART_HOE_HEAD, DummySmeltery.TYPE, new ItemStack[9], "Hoe Head");
    public static final PartTemplate SWORD_BLADE = new PartTemplate(ItemGroups.DUMMY, PART_SWORD_BLADE, DummySmeltery.TYPE, new ItemStack[9], "Sword Blade");
    public static final PartTemplate TOOL_ROD = new PartTemplate(ItemGroups.DUMMY, PART_TOOL_ROD, DummySmeltery.TYPE, new ItemStack[9], "Tool Rod");
    public static final RepairkitTemplate REPAIR_KIT = new RepairkitTemplate(ItemGroups.DUMMY, PART_REPAIR_KIT, DummySmeltery.TYPE, new ItemStack[9], "Repair Kit");
    public static final PartTemplate HELM_PLATE = new PartTemplate(ItemGroups.DUMMY, PART_HELM_PLATES, DummySmeltery.TYPE, new ItemStack[9], "Helmet Plates");
    public static final PartTemplate CHEST_PLATE = new PartTemplate(ItemGroups.DUMMY, PART_CHEST_PLATES, DummySmeltery.TYPE, new ItemStack[9], "Chestplate Plates");
    public static final PartTemplate LEG_PLATE = new PartTemplate(ItemGroups.DUMMY, PART_LEG_PLATES, DummySmeltery.TYPE, new ItemStack[9], "Legging Plates");
    public static final PartTemplate BOOT_PLATE = new PartTemplate(ItemGroups.DUMMY, PART_BOOTS_PLATES, DummySmeltery.TYPE, new ItemStack[9], "Boot Plates");
    public static final PartTemplate MAIL_LINKS = new PartTemplate(ItemGroups.DUMMY, PART_MAIL_LINKS, DummySmeltery.TYPE, new ItemStack[9], "Mail Links");

    public static void set(SlimeTinker p) {

        // Dummies for the recipe book
        new UnplaceableBlock(ItemGroups.PARTS, PART_SHOVEL_HEAD_DUMMY, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_SHOVELHEAD.item())).register(p);
        new UnplaceableBlock(ItemGroups.PARTS, PART_PICKAXE_HEAD_DUMMY, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_PICKAXEHEAD.item())).register(p);
        new UnplaceableBlock(ItemGroups.PARTS, PART_AXE_HEAD_DUMMY, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_AXEHEAD.item())).register(p);
        new UnplaceableBlock(ItemGroups.PARTS, PART_HOE_HEAD_DUMMY, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_HOEHEAD.item())).register(p);
        new UnplaceableBlock(ItemGroups.PARTS, PART_SWORD_BLADE_DUMMY, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_SWORDBLADE.item())).register(p);
        new UnplaceableBlock(ItemGroups.PARTS, PART_TOOL_ROD_DUMMY, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_TOOLROD.item())).register(p);
        new UnplaceableBlock(ItemGroups.PARTS, PART_BINDING_DUMMY, DummyWorkbench.TYPE, new ItemStack[]{
        PART_BINDING_GENERAL_DISPLAY.item(), null, PART_BINDING_GENERAL_DISPLAY.item(),
            null, PART_BINDING_GENERAL_DISPLAY.item(), null,
        PART_BINDING_GENERAL_DISPLAY.item(), null, PART_BINDING_GENERAL_DISPLAY.item()
        }).register(p);
        new UnplaceableBlock(ItemGroups.PARTS, PART_HELM_PLATE_DUMMY, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_HELM_PLATE.item())).register(p);
        new UnplaceableBlock(ItemGroups.PARTS, PART_CHEST_PLATE_DUMMY, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_CHEST_PLATE.item())).register(p);
        new UnplaceableBlock(ItemGroups.PARTS, PART_LEG_PLATE_DUMMY, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_LEG_PLATE.item())).register(p);
        new UnplaceableBlock(ItemGroups.PARTS, PART_BOOT_PLATE_DUMMY, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BOOT_PLATE.item())).register(p);
        new UnplaceableBlock(ItemGroups.PARTS, PART_MAIL_LINKS_DUMMY, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_MAIL_LINK.item())).register(p);
        new UnplaceableBlock(ItemGroups.PARTS, PART_GAMBESON_DUMMY, DummyWorkbench.TYPE, new ItemStack[]{
            null, PART_GAMBESON_GENERAL_DISPLAY.item(), PART_BINDING_GENERAL_DISPLAY.item(),
        PART_GAMBESON_GENERAL_DISPLAY.item(), null, PART_GAMBESON_GENERAL_DISPLAY.item(),
        PART_BINDING_GENERAL_DISPLAY.item(), PART_GAMBESON_GENERAL_DISPLAY.item(), null
        }).register(p);
        new UnplaceableBlock(ItemGroups.PARTS, PART_REPAIR_KIT_DUMMY, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_REPAIRKIT.item())).register(p);

        // Real ones, not in recipe book due to the variations
        SHOVEL_HEAD.register(p);
        PICKAXE_HEAD.register(p);
        AXE_HEAD.register(p);
        HOE_HEAD.register(p);
        SWORD_BLADE.register(p);
        TOOL_ROD.register(p);

        REPAIR_KIT.register(p);

        HELM_PLATE.register(p);
        CHEST_PLATE.register(p);
        LEG_PLATE.register(p);
        BOOT_PLATE.register(p);

    }

}





