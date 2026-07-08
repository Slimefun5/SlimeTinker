package io.github.sefiraat.slimetinker.items;

import io.github.sefiraat.slimetinker.SlimeTinker;
import io.github.sefiraat.slimetinker.itemgroups.ItemGroups;
import io.github.sefiraat.slimetinker.items.workstations.workbench.Workbench;
import io.github.sefiraat.slimetinker.utils.SkullTextures;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.sefiraat.slimetinker.utils.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import org.bukkit.inventory.ItemStack;

public final class Dies {

    private Dies() {
        throw new UnsupportedOperationException("Utility Class");
    }

    public static final String DIE_DESC = "A die to create a cast from";
    public static final SlimefunItemStack DIE_SHOVEL_HEAD =
        new BaseItem("DIE_SHOVEL_HEAD", SkullTextures.PART_SHOVEL_HEAD);
    public static final SlimefunItemStack DIE_PICKAXE_HEAD =
        new BaseItem("DIE_PICKAXE_HEAD", SkullTextures.PART_PICKAXE_HEAD);
    public static final SlimefunItemStack DIE_AXE_HEAD =
        new BaseItem("DIE_AXE_HEAD", SkullTextures.PART_AXE_HEAD);
    public static final SlimefunItemStack DIE_HOE_HEAD =
        new BaseItem("DIE_HOE_HEAD", SkullTextures.PART_HOE_HEAD);
    public static final SlimefunItemStack DIE_SWORD_BLADE =
        new BaseItem("DIE_SWORD_BLADE", SkullTextures.PART_SWORD_BLADE);
    public static final SlimefunItemStack DIE_TOOL_ROD =
        new BaseItem("DIE_TOOL_ROD", SkullTextures.PART_TOOL_ROD);
    public static final SlimefunItemStack DIE_HELM_PLATE =
        new BaseItem("DIE_HELM_PLATE", SkullTextures.PART_HELM_PLATES);
    public static final SlimefunItemStack DIE_CHEST_PLATE =
        new BaseItem("DIE_CHEST_PLATE", SkullTextures.PART_CHEST_PLATES);
    public static final SlimefunItemStack DIE_LEG_PLATE =
        new BaseItem("DIE_LEG_PLATE", SkullTextures.PART_LEG_PLATES);
    public static final SlimefunItemStack DIE_BOOT_PLATE =
        new BaseItem("DIE_BOOT_PLATE", SkullTextures.PART_BOOTS_PLATES);
    public static final SlimefunItemStack DIE_MAIL_LINK =
        new BaseItem("DIE_MAIL_LINK", SkullTextures.PART_LINKS);
    public static final SlimefunItemStack DIE_REPAIR_KIT =
        new BaseItem("DIE_REPAIR_KIT", MaterialCompat.safe(XMaterial.MINECART));
    protected static final ItemStack[] RECIPE_DIE_SHOVEL_HEAD = new ItemStack[]{
        null, new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), null,
        null, null, null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_DIE_PICKAXE_HEAD = new ItemStack[]{
        new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)),
        null, null, null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_DIE_AXE_HEAD = new ItemStack[]{
        new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), null,
        new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), null, null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_DIE_HOE_HEAD = new ItemStack[]{
        new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), null,
        null, null, null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_DIE_SWORD_BLADE = new ItemStack[]{
        null, new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), null,
        null, new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_DIE_TOOL_ROD = new ItemStack[]{
        null, null, null,
        null, new ItemStack(MaterialCompat.safe(XMaterial.STICK)), null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_DIE_HELM_PLATE = new ItemStack[]{
        new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)),
        new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), null, new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)),
        null, null, null
    };
    protected static final ItemStack[] RECIPE_DIE_CHEST_PLATE = new ItemStack[]{
        new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), null, new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)),
        new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)),
        new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE))
    };
    protected static final ItemStack[] RECIPE_DIE_LEG_PLATE = new ItemStack[]{
        new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)),
        new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), null, new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)),
        new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), null, new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE))
    };
    protected static final ItemStack[] RECIPE_DIE_BOOT_PLATE = new ItemStack[]{
        null, null, null,
        new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), null, new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)),
        new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), null, new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE))
    };
    protected static final ItemStack[] RECIPE_DIE_MAIL_LINKS = new ItemStack[]{
        null, null, null,
        null, new ItemStack(MaterialCompat.safe(XMaterial.IRON_NUGGET)), null,
        null, null, null
    };
    protected static final ItemStack[] RECIPE_DIE_REPAIR_KIT = new ItemStack[]{
        null, null, null,
        null, new ItemStack(MaterialCompat.safe(XMaterial.OBSIDIAN)), null,
        null, null, null
    };

    public static void set(SlimeTinker p) {
        new UnplaceableBlock(ItemGroups.CASTS, DIE_SHOVEL_HEAD, Workbench.TYPE, RECIPE_DIE_SHOVEL_HEAD).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, DIE_PICKAXE_HEAD, Workbench.TYPE, RECIPE_DIE_PICKAXE_HEAD).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, DIE_AXE_HEAD, Workbench.TYPE, RECIPE_DIE_AXE_HEAD).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, DIE_HOE_HEAD, Workbench.TYPE, RECIPE_DIE_HOE_HEAD).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, DIE_SWORD_BLADE, Workbench.TYPE, RECIPE_DIE_SWORD_BLADE).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, DIE_TOOL_ROD, Workbench.TYPE, RECIPE_DIE_TOOL_ROD).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, DIE_REPAIR_KIT, Workbench.TYPE, RECIPE_DIE_REPAIR_KIT).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, DIE_HELM_PLATE, Workbench.TYPE, RECIPE_DIE_HELM_PLATE).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, DIE_CHEST_PLATE, Workbench.TYPE, RECIPE_DIE_CHEST_PLATE).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, DIE_LEG_PLATE, Workbench.TYPE, RECIPE_DIE_LEG_PLATE).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, DIE_BOOT_PLATE, Workbench.TYPE, RECIPE_DIE_BOOT_PLATE).register(p);
        new UnplaceableBlock(ItemGroups.CASTS, DIE_MAIL_LINK, Workbench.TYPE, RECIPE_DIE_MAIL_LINKS).register(p);
    }
}



