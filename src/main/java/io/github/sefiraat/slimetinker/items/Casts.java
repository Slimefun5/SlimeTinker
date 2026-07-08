package io.github.sefiraat.slimetinker.items;

import io.github.sefiraat.slimetinker.SlimeTinker;
import io.github.sefiraat.slimetinker.itemgroups.ItemGroups;
import io.github.sefiraat.slimetinker.items.tinkermaterials.TinkerMaterialManager;
import io.github.sefiraat.slimetinker.items.workstations.smeltery.DummySmeltery;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.implementation.SlimefunItems;
import io.github.sefiraat.slimetinker.utils.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

public final class Casts {

    private Casts() {
        throw new UnsupportedOperationException("Utility Class");
    }

    public static final String CAST_DESC = "A cast for metals.";
    public static final String INPUT_DESC = "Input : ";
    public static final String OUTPUT_DESC = "Output : ";
    public static final String UNIT_DESC = " Units";
    public static final SlimefunItemStack CAST_NUGGET =
        new BaseItem("CAST_NUGGET", MaterialCompat.safe(XMaterial.YELLOW_CARPET));
    public static final SlimefunItemStack CAST_BLOCK =
        new BaseItem("CAST_BLOCK", MaterialCompat.safe(XMaterial.YELLOW_CARPET));
    public static final SlimefunItemStack CAST_INGOT =
        new BaseItem("CAST_INGOT", MaterialCompat.safe(XMaterial.YELLOW_CARPET));
    public static final SlimefunItemStack CAST_GEM =
        new BaseItem("CAST_GEM", MaterialCompat.safe(XMaterial.YELLOW_CARPET));
    public static final SlimefunItemStack CAST_SHOVELHEAD =
        new BaseItem("CAST_SHOVELHEAD", MaterialCompat.safe(XMaterial.ORANGE_CARPET));
    public static final SlimefunItemStack CAST_PICKAXEHEAD =
        new BaseItem("CAST_PICKAXEHEAD", MaterialCompat.safe(XMaterial.ORANGE_CARPET));
    public static final SlimefunItemStack CAST_AXEHEAD =
        new BaseItem("CAST_AXE_HEAD", MaterialCompat.safe(XMaterial.ORANGE_CARPET));
    public static final SlimefunItemStack CAST_HOEHEAD =
        new BaseItem("CAST_HOEHEAD", MaterialCompat.safe(XMaterial.ORANGE_CARPET));
    public static final SlimefunItemStack CAST_SWORDBLADE =
        new BaseItem("CAST_SWORDBLADE", MaterialCompat.safe(XMaterial.ORANGE_CARPET));
    public static final SlimefunItemStack CAST_TOOLROD =
        new BaseItem("CAST_TOOLROD", MaterialCompat.safe(XMaterial.ORANGE_CARPET));
    public static final SlimefunItemStack CAST_HELM_PLATE =
        new BaseItem("CAST_HELM_PLATE", MaterialCompat.safe(XMaterial.ORANGE_CARPET));
    public static final SlimefunItemStack CAST_CHEST_PLATE =
        new BaseItem("CAST_CHEST_PLATE", MaterialCompat.safe(XMaterial.ORANGE_CARPET));
    public static final SlimefunItemStack CAST_LEG_PLATE =
        new BaseItem("CAST_LEG_PLATE", MaterialCompat.safe(XMaterial.ORANGE_CARPET));
    public static final SlimefunItemStack CAST_BOOT_PLATE =
        new BaseItem("CAST_BOOT_PLATE", MaterialCompat.safe(XMaterial.ORANGE_CARPET));
    public static final SlimefunItemStack CAST_MAIL_LINK =
        new BaseItem("CAST_MAIL_LINK", MaterialCompat.safe(XMaterial.ORANGE_CARPET));
    public static final SlimefunItemStack CAST_REPAIRKIT =
        new BaseItem("CAST_REPAIRKIT", MaterialCompat.safe(XMaterial.YELLOW_CARPET));
    protected static final ItemStack[] RECIPE_CAST_NUGGET = new ItemStack[]{
        new ItemStack(MaterialCompat.safe(XMaterial.GOLD_INGOT), 2), new ItemStack(MaterialCompat.safe(XMaterial.IRON_NUGGET)), null,
        null, null, null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_CAST_INGOT = new ItemStack[]{
        new ItemStack(MaterialCompat.safe(XMaterial.GOLD_INGOT), 2), new ItemStack(MaterialCompat.safe(XMaterial.BRICK)), null,
        null, null, null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_CAST_BLOCK = new ItemStack[]{
        new ItemStack(MaterialCompat.safe(XMaterial.GOLD_INGOT), 2), new ItemStack(MaterialCompat.safe(XMaterial.STONE)), null,
        null, null, null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_CAST_GEM = new ItemStack[]{
        new ItemStack(MaterialCompat.safe(XMaterial.GOLD_INGOT), 2), new ItemStack(MaterialCompat.safe(XMaterial.DIAMOND)), null,
        null, null, null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_CAST_SHOVEL_HEAD = new ItemStack[]{
        SlimefunItems.BRASS_INGOT.asQuantity(2), Dies.DIE_SHOVEL_HEAD.item(), null,
        null, null, null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_CAST_PICKAXE_HEAD = new ItemStack[]{
        SlimefunItems.BRASS_INGOT.asQuantity(2), Dies.DIE_PICKAXE_HEAD.item(), null,
        null, null, null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_CAST_AXE_HEAD = new ItemStack[]{
        SlimefunItems.BRASS_INGOT.asQuantity(2), Dies.DIE_AXE_HEAD.item(), null,
        null, null, null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_CAST_HOE_HEAD = new ItemStack[]{
        SlimefunItems.BRASS_INGOT.asQuantity(2), Dies.DIE_HOE_HEAD.item(), null,
        null, null, null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_CAST_SWORD_BLADE = new ItemStack[]{
        SlimefunItems.BRASS_INGOT.asQuantity(2), Dies.DIE_SWORD_BLADE.item(), null,
        null, null, null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_CAST_TOOL_ROD = new ItemStack[]{
        SlimefunItems.BRASS_INGOT.asQuantity(2), Dies.DIE_TOOL_ROD.item(), null,
        null, null, null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_CAST_HELM_PLATE = new ItemStack[]{
        SlimefunItems.BRASS_INGOT.asQuantity(2), Dies.DIE_HELM_PLATE.item(), null,
        null, null, null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_CAST_CHEST_PLATE = new ItemStack[]{
        SlimefunItems.BRASS_INGOT.asQuantity(2), Dies.DIE_CHEST_PLATE.item(), null,
        null, null, null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_CAST_LEG_PLATE = new ItemStack[]{
        SlimefunItems.BRASS_INGOT.asQuantity(2), Dies.DIE_LEG_PLATE.item(), null,
        null, null, null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_CAST_BOOT_PLATE = new ItemStack[]{
        SlimefunItems.BRASS_INGOT.asQuantity(2), Dies.DIE_BOOT_PLATE.item(), null,
        null, null, null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_CAST_MAIL_LINK = new ItemStack[]{
        SlimefunItems.BRASS_INGOT.asQuantity(2), Dies.DIE_MAIL_LINK.item(), null,
        null, null, null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_CAST_REPAIR_KIT = new ItemStack[]{
        new ItemStack(MaterialCompat.safe(XMaterial.GOLD_INGOT), 2), Dies.DIE_REPAIR_KIT.item(), null,
        null, null, null,
        null, null, null
    };

    public static void set(SlimeTinker p) {

        new UnplaceableBlock(ItemGroups.CASTS, CAST_NUGGET, DummySmeltery.TYPE, RECIPE_CAST_NUGGET).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, CAST_INGOT, DummySmeltery.TYPE, RECIPE_CAST_INGOT).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, CAST_BLOCK, DummySmeltery.TYPE, RECIPE_CAST_BLOCK).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, CAST_GEM, DummySmeltery.TYPE, RECIPE_CAST_GEM).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, CAST_SHOVELHEAD, DummySmeltery.TYPE, RECIPE_CAST_SHOVEL_HEAD).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, CAST_PICKAXEHEAD, DummySmeltery.TYPE, RECIPE_CAST_PICKAXE_HEAD).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, CAST_AXEHEAD, DummySmeltery.TYPE, RECIPE_CAST_AXE_HEAD).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, CAST_HOEHEAD, DummySmeltery.TYPE, RECIPE_CAST_HOE_HEAD).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, CAST_SWORDBLADE, DummySmeltery.TYPE, RECIPE_CAST_SWORD_BLADE).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, CAST_TOOLROD, DummySmeltery.TYPE, RECIPE_CAST_TOOL_ROD).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, CAST_HELM_PLATE, DummySmeltery.TYPE, RECIPE_CAST_HELM_PLATE).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, CAST_CHEST_PLATE, DummySmeltery.TYPE, RECIPE_CAST_CHEST_PLATE).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, CAST_LEG_PLATE, DummySmeltery.TYPE, RECIPE_CAST_LEG_PLATE).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, CAST_BOOT_PLATE, DummySmeltery.TYPE, RECIPE_CAST_BOOT_PLATE).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, CAST_MAIL_LINK, DummySmeltery.TYPE, RECIPE_CAST_MAIL_LINK).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, CAST_REPAIRKIT, DummySmeltery.TYPE, RECIPE_CAST_REPAIR_KIT).register(p);

    }
}





