package io.github.sefiraat.slimetinker.items;

import io.github.sefiraat.slimetinker.SlimeTinker;
import io.github.sefiraat.slimetinker.itemgroups.ItemGroups;
import io.github.sefiraat.slimetinker.items.workstations.armourtable.ArmourTable;
import io.github.sefiraat.slimetinker.items.workstations.modificationstation.ModificationStation;
import io.github.sefiraat.slimetinker.items.workstations.repairbench.RepairBench;
import io.github.sefiraat.slimetinker.items.workstations.smeltery.DummySmelteryMulti;
import io.github.sefiraat.slimetinker.items.workstations.swappingstation.SwappingStation;
import io.github.sefiraat.slimetinker.items.workstations.tooltable.ToolTable;
import io.github.sefiraat.slimetinker.items.workstations.workbench.Workbench;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.implementation.SlimefunItems;
import io.github.sefiraat.slimetinker.utils.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public final class Workstations {

    private Workstations() {
        throw new UnsupportedOperationException("Utility Class");
    }

    // Tinkers Smeltery
    public static final SlimefunItemStack TINKERS_SMELTERY_CORE = new BaseItem("TINKERS_SMELTERY_CORE", MaterialCompat.safe(XMaterial.CHISELED_POLISHED_BLACKSTONE));

    // Workbench
    public static final SlimefunItemStack TINKERS_WORKBENCH = new BaseItem("TINKERS_WORKBENCH", MaterialCompat.safe(XMaterial.FLETCHING_TABLE));

    // Tool Table
    public static final SlimefunItemStack TINKERS_TABLE = new BaseItem("TINKERS_TABLE", MaterialCompat.safe(XMaterial.SMITHING_TABLE));

    // Armour Table
    public static final SlimefunItemStack TINKERS_ARMOUR_TABLE = new BaseItem("TINKERS_ARMOUR_TABLE", MaterialCompat.safe(XMaterial.SMITHING_TABLE));

    // Repair
    public static final SlimefunItemStack TINKERS_REPAIR_BENCH = new BaseItem("TINKERS_REPAIR_BENCH", MaterialCompat.safe(XMaterial.CARTOGRAPHY_TABLE));

    // Swapping
    public static final SlimefunItemStack TINKERS_SWAPPING_STATION = new BaseItem("TINKERS_SWAPPING_STATION", MaterialCompat.safe(XMaterial.LOOM));

    // Modification
    public static final SlimefunItemStack TINKERS_MOD_STATION = new BaseItem("TINKERS_MOD_STATION", MaterialCompat.safe(XMaterial.GRINDSTONE));

    private static final ItemStack[] RECIPE_TINKERS_SMELTERY_MULTI = new ItemStack[]{
        Materials.SEARED_BRICK_BLOCK.item(), Materials.SEARED_BRICK_BLOCK.item(), Materials.SEARED_BRICK_BLOCK.item(),
        Materials.SEARED_TANK.item(), Materials.SMELTERY_CONTROLLER.item(), Materials.SPOUT.item(),
        Materials.SEARED_BRICK_BLOCK.item(), Materials.SEARED_BRICK_BLOCK.item(), Materials.SEARED_BRICK_BLOCK.item()
    };

    private static final ItemStack[] RECIPE_TINKERS_WORKBENCH = new ItemStack[]{
        new ItemStack(MaterialCompat.safe(XMaterial.IRON_BLOCK)), new ItemStack(MaterialCompat.safe(XMaterial.IRON_BLOCK)), new ItemStack(MaterialCompat.safe(XMaterial.IRON_BLOCK)),
        new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS)), new ItemStack(MaterialCompat.safe(XMaterial.CRAFTING_TABLE)), new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS)),
        new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS)), new ItemStack(MaterialCompat.safe(XMaterial.DISPENSER)), new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS))
    };

    private static final ItemStack[] RECIPE_TINKERS_TABLE = new ItemStack[]{
        Materials.BLOCK_CAST_STEEL.item(), Materials.BLOCK_CAST_STEEL.item(), Materials.BLOCK_CAST_STEEL.item(),
        new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS)), Workstations.TINKERS_WORKBENCH.item(), new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS)),
        new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS)), SlimefunItems.POWER_CRYSTAL.item(), new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS))
    };

    private static final ItemStack[] RECIPE_TINKERS_ARMOUR_TABLE = new ItemStack[]{
        Materials.BLOCK_CAST_DAMASCUS_STEEL.item(), Materials.BLOCK_CAST_DAMASCUS_STEEL.item(), Materials.BLOCK_CAST_DAMASCUS_STEEL.item(),
        new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS)), Workstations.TINKERS_WORKBENCH.item(), new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS)),
        new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS)), SlimefunItems.POWER_CRYSTAL.item(), new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS))
    };

    private static final ItemStack[] RECIPE_TINKERS_REPAIR_BENCH = new ItemStack[]{
        Materials.BLOCK_CAST_ALUMINUM.item(), Materials.BLOCK_CAST_ALUMINUM.item(), Materials.BLOCK_CAST_ALUMINUM.item(),
        new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS)), Workstations.TINKERS_WORKBENCH.item(), new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS)),
        new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS)), SlimefunItems.POWER_CRYSTAL.item(), new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS))
    };

    private static final ItemStack[] RECIPE_TINKERS_SWAPPING_STATION = new ItemStack[]{
        Materials.BLOCK_CAST_COR_BRONZE.item(), Materials.BLOCK_CAST_COR_BRONZE.item(), Materials.BLOCK_CAST_COR_BRONZE.item(),
        new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS)), Workstations.TINKERS_WORKBENCH.item(), new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS)),
        new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS)), SlimefunItems.POWER_CRYSTAL.item(), new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS))
    };

    private static final ItemStack[] RECIPE_TINKERS_MOD_STATION = new ItemStack[]{
        Materials.BLOCK_CAST_REINFORCED.item(), Materials.BLOCK_CAST_REINFORCED.item(), Materials.BLOCK_CAST_REINFORCED.item(),
        new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS)), Workstations.TINKERS_TABLE.item(), new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS)),
        new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS)), SlimefunItems.POWER_CRYSTAL.item(), new ItemStack(MaterialCompat.safe(XMaterial.OAK_PLANKS))
    };

    public static void set(@Nonnull SlimeTinker p) {

        // Setting the workbench to the plugin so during the first tick we can load additional recipes from other plugins
        p.setWorkbench(new Workbench(ItemGroups.WORKSTATIONS, TINKERS_WORKBENCH, RecipeType.ENHANCED_CRAFTING_TABLE, RECIPE_TINKERS_WORKBENCH));
        p.getWorkbench().register(p);

        new UnplaceableBlock(ItemGroups.WORKSTATIONS, TINKERS_SMELTERY_CORE, DummySmelteryMulti.TYPE, RECIPE_TINKERS_SMELTERY_MULTI).register(p);
        new ToolTable(ItemGroups.WORKSTATIONS, TINKERS_TABLE, Workbench.TYPE, RECIPE_TINKERS_TABLE).register(p);
        new ArmourTable(ItemGroups.WORKSTATIONS, TINKERS_ARMOUR_TABLE, Workbench.TYPE, RECIPE_TINKERS_ARMOUR_TABLE).register(p);
        new RepairBench(ItemGroups.WORKSTATIONS, TINKERS_REPAIR_BENCH, Workbench.TYPE, RECIPE_TINKERS_REPAIR_BENCH).register(p);
        new SwappingStation(ItemGroups.WORKSTATIONS, TINKERS_SWAPPING_STATION, Workbench.TYPE, RECIPE_TINKERS_SWAPPING_STATION).register(p);
        new ModificationStation(ItemGroups.WORKSTATIONS, TINKERS_MOD_STATION, Workbench.TYPE, RECIPE_TINKERS_MOD_STATION).register(p);
    }
}

