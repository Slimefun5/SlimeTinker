package io.github.sefiraat.slimetinker.items;

import io.github.sefiraat.slimetinker.SlimeTinker;
import io.github.sefiraat.slimetinker.itemgroups.ItemGroups;
import io.github.sefiraat.slimetinker.items.workstations.smeltery.DummySmeltery;
import io.github.sefiraat.slimetinker.items.workstations.smeltery.TinkersSmeltery;
import io.github.sefiraat.slimetinker.items.workstations.workbench.Workbench;
import io.github.sefiraat.slimetinker.managers.SupportedPluginsManager;
import io.github.sefiraat.slimetinker.utils.ItemUtils;
import io.github.sefiraat.slimetinker.utils.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import org.bukkit.inventory.ItemStack;

public final class Materials {

    private Materials() {
        throw new UnsupportedOperationException("Utility Class");
    }

    // region Vanilla + Core SF

    // region Nuggets
    public static final SlimefunItemStack NUGGET_CAST_COPPER = new BaseItem("NUGGET_CAST_COPPER", MaterialCompat.safe(XMaterial.ACACIA_BUTTON));

    public static final SlimefunItemStack NUGGET_CAST_TIN = new BaseItem("NUGGET_CAST_TIN", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_ZINC = new BaseItem("NUGGET_CAST_ZINC", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_ALUMINUM = new BaseItem("NUGGET_CAST_ALUMINUM", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_MAGNESIUM = new BaseItem("NUGGET_CAST_MAGNESIUM", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_LEAD = new BaseItem("NUGGET_CAST_LEAD", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_SILVER = new BaseItem("NUGGET_CAST_SILVER", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_COAL = new BaseItem("NUGGET_CAST_COAL", MaterialCompat.safe(XMaterial.POLISHED_BLACKSTONE_BUTTON));

    public static final SlimefunItemStack NUGGET_CAST_STEEL = new BaseItem("NUGGET_CAST_STEEL", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_DAMASCUS_STEEL = new BaseItem("NUGGET_CAST_DAMASCUS_STEEL", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_DURALIUM = new BaseItem("NUGGET_CAST_DURALIUM", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_BRONZE = new BaseItem("NUGGET_CAST_BRONZE", MaterialCompat.safe(XMaterial.ACACIA_BUTTON));

    public static final SlimefunItemStack NUGGET_CAST_ALU_BRONZE = new BaseItem("NUGGET_CAST_ALU_BRONZE", MaterialCompat.safe(XMaterial.GOLD_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_HARD_METAL = new BaseItem("NUGGET_CAST_HARD_METAL", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_COR_BRONZE = new BaseItem("NUGGET_CAST_COR_BRONZE", MaterialCompat.safe(XMaterial.GOLD_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_SOLDER = new BaseItem("NUGGET_CAST_SOLDER", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_BILLON = new BaseItem("NUGGET_CAST_BILLON", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_BRASS = new BaseItem("NUGGET_CAST_BRASS", MaterialCompat.safe(XMaterial.GOLD_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_ALU_BRASS = new BaseItem("NUGGET_CAST_ALU_BRASS", MaterialCompat.safe(XMaterial.GOLD_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_NICKEL = new BaseItem("NUGGET_CAST_NICKEL", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_COBALT = new BaseItem("NUGGET_CAST_COBALT", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_REINFORCED = new BaseItem("NUGGET_CAST_REINFORCED", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_FERROSILICON = new BaseItem("NUGGET_CAST_FERROSILICON", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_REDSTONE_ALLOY = new BaseItem("NUGGET_CAST_REDSTONE_ALLOY", MaterialCompat.safe(XMaterial.ACACIA_BUTTON));

    public static final SlimefunItemStack NUGGET_CAST_BOOMERITE = new BaseItem("NUGGET_CAST_BOOMERITE", MaterialCompat.safe(XMaterial.ACACIA_BUTTON));

    public static final SlimefunItemStack NUGGET_CAST_SEFIRITE = new BaseItem("NUGGET_CAST_SEFIRITE", MaterialCompat.safe(XMaterial.IRON_NUGGET));
    public static final SlimefunItemStack NUGGET_CAST_CRINGLEIUM = new BaseItem("NUGGET_CAST_CRINGLEIUM", MaterialCompat.safe(XMaterial.IRON_NUGGET));
    public static final SlimefunItemStack NUGGET_CAST_FONDNESS = new BaseItem("NUGGET_CAST_FONDNESS", MaterialCompat.safe(XMaterial.PINK_DYE));
    public static final SlimefunItemStack NUGGET_CAST_DEVOTION = new BaseItem("NUGGET_CAST_DEVOTION", MaterialCompat.safe(XMaterial.PINK_DYE));
    public static final SlimefunItemStack NUGGET_CAST_PASSION = new BaseItem("NUGGET_CAST_PASSION", MaterialCompat.safe(XMaterial.PINK_DYE));
    public static final SlimefunItemStack NUGGET_CAST_LOVE = new BaseItem("NUGGET_CAST_LOVE", MaterialCompat.safe(XMaterial.PINK_DYE));
    public static final SlimefunItemStack NUGGET_CAST_NICE = new BaseItem("NUGGET_CAST_NICE", MaterialCompat.safe(XMaterial.GOLD_NUGGET));
    public static final SlimefunItemStack NUGGET_CAST_SMITHIUM = new BaseItem("NUGGET_CAST_SMITHIUM", MaterialCompat.safe(XMaterial.IRON_NUGGET));
    public static final SlimefunItemStack NUGGET_CAST_ANNIVERSARIUM = new BaseItem("NUGGET_CAST_ANNIVERSARIUM", MaterialCompat.safe(XMaterial.GOLD_NUGGET));
    public static final SlimefunItemStack NUGGET_CAST_REINFORCED_DRACONIUM = new BaseItem("NUGGET_CAST_REINFORCED_DRACONIUM", MaterialCompat.safe(XMaterial.CRIMSON_BUTTON));
    // endregion

    // region Ingots
    public static final SlimefunItemStack INGOT_CAST_BOOMERITE = new BaseItem("INGOT_CAST_BOOMERITE", MaterialCompat.safe(XMaterial.BRICK));
    public static final SlimefunItemStack INGOT_CAST_SEFIRITE = new BaseItem("INGOT_CAST_SEFIRITE", MaterialCompat.safe(XMaterial.IRON_INGOT));
    public static final SlimefunItemStack INGOT_CAST_CRINGLEIUM = new BaseItem("INGOT_CAST_CRINGLEIUM", MaterialCompat.safe(XMaterial.IRON_INGOT));
    public static final SlimefunItemStack INGOT_CAST_FONDNESS = new BaseItem("INGOT_CAST_FONDNESS", MaterialCompat.safe(XMaterial.BRICK));
    public static final SlimefunItemStack INGOT_CAST_DEVOTION = new BaseItem("INGOT_CAST_DEVOTION", MaterialCompat.safe(XMaterial.BRICK));
    public static final SlimefunItemStack INGOT_CAST_PASSION = new BaseItem("INGOT_CAST_PASSION", MaterialCompat.safe(XMaterial.BRICK));
    public static final SlimefunItemStack INGOT_CAST_LOVE = new BaseItem("INGOT_CAST_LOVE", MaterialCompat.safe(XMaterial.BRICK));
    public static final SlimefunItemStack INGOT_CAST_NICE = new BaseItem("INGOT_CAST_NICE", MaterialCompat.safe(XMaterial.GOLD_INGOT));
    public static final SlimefunItemStack INGOT_CAST_SMITHIUM = new BaseItem("INGOT_CAST_SMITHIUM", MaterialCompat.safe(XMaterial.IRON_INGOT));
    public static final SlimefunItemStack INGOT_CAST_ANNIVERSARIUM = new BaseItem("INGOT_CAST_ANNIVERSARIUM", MaterialCompat.safe(XMaterial.GOLD_INGOT));
    public static final SlimefunItemStack INGOT_CAST_REINFORCED_DRACONIUM = new BaseItem("INGOT_CAST_REINFORCED_DRACONIUM", MaterialCompat.safe(XMaterial.BRICK));
    // endregion

    // region Blocks
    public static final SlimefunItemStack BLOCK_CAST_COPPER = new BaseItem("BLOCK_CAST_COPPER", MaterialCompat.safe(XMaterial.TERRACOTTA));

    public static final SlimefunItemStack BLOCK_CAST_TIN = new BaseItem("BLOCK_CAST_TIN", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_ZINC = new BaseItem("BLOCK_CAST_ZINC", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_ALUMINUM = new BaseItem("BLOCK_CAST_ALUMINUM", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_MAGNESIUM = new BaseItem("BLOCK_CAST_MAGNESIUM", MaterialCompat.safe(XMaterial.PINK_TERRACOTTA));

    public static final SlimefunItemStack BLOCK_CAST_LEAD = new BaseItem("BLOCK_CAST_LEAD", MaterialCompat.safe(XMaterial.PURPLE_TERRACOTTA));

    public static final SlimefunItemStack BLOCK_CAST_SILVER = new BaseItem("BLOCK_CAST_SILVER", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_STEEL = new BaseItem("BLOCK_CAST_STEEL", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_DAMASCUS_STEEL = new BaseItem("BLOCK_CAST_DAMASCUS_STEEL", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_DURALIUM = new BaseItem("BLOCK_CAST_DURALIUM", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_BRONZE = new BaseItem("BLOCK_CAST_BRONZE", MaterialCompat.safe(XMaterial.TERRACOTTA));

    public static final SlimefunItemStack BLOCK_CAST_ALU_BRONZE = new BaseItem("BLOCK_CAST_ALU_BRONZE", MaterialCompat.safe(XMaterial.TERRACOTTA));

    public static final SlimefunItemStack BLOCK_CAST_HARD_METAL = new BaseItem("BLOCK_CAST_HARD_METAL", MaterialCompat.safe(XMaterial.IRON_BLOCK));
    public static final SlimefunItemStack BLOCK_CAST_COR_BRONZE = new BaseItem("BLOCK_CAST_COR_BRONZE", MaterialCompat.safe(XMaterial.TERRACOTTA));

    public static final SlimefunItemStack BLOCK_CAST_SOLDER = new BaseItem("BLOCK_CAST_SOLDER", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_BILLON = new BaseItem("BLOCK_CAST_BILLON", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_BRASS = new BaseItem("BLOCK_CAST_BRASS", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_ALU_BRASS = new BaseItem("BLOCK_CAST_ALU_BRASS", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_NICKEL = new BaseItem("BLOCK_CAST_NICKEL", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_COBALT = new BaseItem("BLOCK_CAST_COBALT", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_REINFORCED = new BaseItem("BLOCK_CAST_REINFORCED", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_FERROSILICON = new BaseItem("BLOCK_CAST_FERROSILICON", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_REDSTONE_ALLOY = new BaseItem("BLOCK_CAST_REDSTONE_ALLOY", MaterialCompat.safe(XMaterial.TERRACOTTA));

    public static final SlimefunItemStack BLOCK_CAST_BOOMERITE = new BaseItem("BLOCK_CAST_BOOMERITE", MaterialCompat.safe(XMaterial.TERRACOTTA));

    public static final SlimefunItemStack BLOCK_CAST_SEFIRITE = new BaseItem("BLOCK_CAST_SEFIRITE", MaterialCompat.safe(XMaterial.IRON_BLOCK));
    public static final SlimefunItemStack BLOCK_CAST_CRINGLEIUM = new BaseItem("BLOCK_CAST_CRINGLEIUM", MaterialCompat.safe(XMaterial.IRON_BLOCK));
    public static final SlimefunItemStack BLOCK_CAST_LOVE = new BaseItem("BLOCK_CAST_LOVE", MaterialCompat.safe(XMaterial.PINK_DYE));
    public static final SlimefunItemStack BLOCK_CAST_NICE = new BaseItem("BLOCK_CAST_NICE", MaterialCompat.safe(XMaterial.GOLD_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_SMITHIUM = new BaseItem("BLOCK_CAST_SMITHIUM", MaterialCompat.safe(XMaterial.IRON_BLOCK));
    public static final SlimefunItemStack BLOCK_CAST_ANNIVERSARIUM = new BaseItem("BLOCK_CAST_ANNIVERSARIUM", MaterialCompat.safe(XMaterial.GOLD_BLOCK));
    public static final SlimefunItemStack BLOCK_CAST_REINFORCED_DRACONIUM = new BaseItem("BLOCK_CAST_REINFORCED_DRACONIUM", MaterialCompat.safe(XMaterial.RED_CONCRETE));
    // endregion

    // region Crafts
    public static final SlimefunItemStack MOD_PLATE = new BaseItem("MOD_PLATE", MaterialCompat.safe(XMaterial.OBSIDIAN));

    public static final SlimefunItemStack GROUT = new BaseItem("GROUT", MaterialCompat.safe(XMaterial.GRAVEL));


    public static final SlimefunItemStack SEARED_BRICK = new BaseItem("SEARED_BRICK", MaterialCompat.safe(XMaterial.BRICK));

    public static final SlimefunItemStack SMELTERY_CONTROLLER = new BaseItem("SMELTERY_CONTROLLER", MaterialCompat.safe(XMaterial.CHISELED_POLISHED_BLACKSTONE));

    public static final SlimefunItemStack SEARED_TANK = new BaseItem("SEARED_TANK", MaterialCompat.safe(XMaterial.RED_NETHER_BRICK_WALL));

    public static final SlimefunItemStack SPOUT = new BaseItem("SPOUT", MaterialCompat.safe(XMaterial.POLISHED_BLACKSTONE_BRICK_WALL));

    public static final SlimefunItemStack SEARED_BRICK_BLOCK = new BaseItem("SEARED_BRICK_BLOCK", MaterialCompat.safe(XMaterial.POLISHED_BLACKSTONE_BRICKS));
    // endregion

    // endregion

    // region Infinity Expansion

    // region Nuggets
    public static final SlimefunItemStack NUGGET_CAST_INFINITY = new BaseItem("NUGGET_CAST_INFINITY", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_MAGSTEEL = new BaseItem("NUGGET_CAST_MAGSTEEL", MaterialCompat.safe(XMaterial.ACACIA_BUTTON));

    public static final SlimefunItemStack NUGGET_CAST_TITANIUM = new BaseItem("NUGGET_CAST_TITANIUM", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_MYTHRIL = new BaseItem("NUGGET_CAST_MYTHRIL", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_ADAMANTITE = new BaseItem("NUGGET_CAST_ADAMANTITE", MaterialCompat.safe(XMaterial.ACACIA_BUTTON));

    public static final SlimefunItemStack NUGGET_CAST_MAGNONIUM = new BaseItem("NUGGET_CAST_MAGNONIUM", MaterialCompat.safe(XMaterial.CRIMSON_BUTTON));

    // endregion

    // region Blocks
    public static final SlimefunItemStack BLOCK_CAST_VOID = new BaseItem("BLOCK_CAST_VOID", MaterialCompat.safe(XMaterial.NETHERITE_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_INFINITY = new BaseItem("BLOCK_CAST_INFINITY", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_MAGSTEEL = new BaseItem("BLOCK_CAST_MAGSTEEL", MaterialCompat.safe(XMaterial.TERRACOTTA));

    public static final SlimefunItemStack BLOCK_CAST_TITANIUM = new BaseItem("BLOCK_CAST_TITANIUM", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_MYTHRIL = new BaseItem("BLOCK_CAST_MYTHRIL", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_ADAMANTITE = new BaseItem("BLOCK_CAST_ADAMANTITE", MaterialCompat.safe(XMaterial.TERRACOTTA));

    public static final SlimefunItemStack BLOCK_CAST_MAGNONIUM = new BaseItem("BLOCK_CAST_MAGNONIUM", MaterialCompat.safe(XMaterial.PURPLE_TERRACOTTA));

    // endregion

    // endregion

    // region SFWarfare

    // region Nuggets
    public static final SlimefunItemStack NUGGET_CAST_SLIMESTEEL = new BaseItem("NUGGET_CAST_SLIMESTEEL", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_REINFORCED_SLIMESTEEL = new BaseItem("NUGGET_CAST_REINFORCED_SLIMESTEEL", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_OSMIUM = new BaseItem("NUGGET_CAST_OSMIUM", MaterialCompat.safe(XMaterial.WARPED_BUTTON));

    public static final SlimefunItemStack NUGGET_CAST_OSMIUM_SUPER_ALLOY = new BaseItem("NUGGET_CAST_OSMIUM_SUPER_ALLOY", MaterialCompat.safe(XMaterial.WARPED_BUTTON));

    public static final SlimefunItemStack NUGGET_CAST_UNPATENTABILUM = new BaseItem("NUGGET_CAST_UNPATENTABILUM", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    // endregion

    // region Blocks
    public static final SlimefunItemStack BLOCK_CAST_SLIMESTEEL = new BaseItem("BLOCK_CAST_SLIMESTEEL", MaterialCompat.safe(XMaterial.SLIME_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_REINFORCED_SLIMESTEEL = new BaseItem("BLOCK_CAST_REINFORCED_SLIMESTEEL", MaterialCompat.safe(XMaterial.SLIME_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_OSMIUM = new BaseItem("BLOCK_CAST_OSMIUM", MaterialCompat.safe(XMaterial.LIGHT_BLUE_TERRACOTTA));

    public static final SlimefunItemStack BLOCK_CAST_OSMIUM_SUPER_ALLOY = new BaseItem("BLOCK_CAST_OSMIUM_SUPER_ALLOY", MaterialCompat.safe(XMaterial.BLUE_TERRACOTTA));

    public static final SlimefunItemStack BLOCK_CAST_UNPATENTABILUM = new BaseItem("BLOCK_CAST_UNPATENTABILUM", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    // endregion

    // endregion

    // region DynaTech

    // region Nuggets
    public static final SlimefunItemStack NUGGET_CAST_STAINLESSSTEEL = new BaseItem("NUGGET_CAST_STAINLESSSTEEL", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    // endregion

    // region Blocks
    public static final SlimefunItemStack BLOCK_CAST_STAINLESSSTEEL = new BaseItem("BLOCK_CAST_STAINLESSSTEEL", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    // endregion

    // endregion

    // region LiteXpansion

    // region Nuggets
    public static final SlimefunItemStack NUGGET_CAST_REFINED_IRON = new BaseItem("NUGGET_CAST_REFINED_IRON", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_MIXED_METAL = new BaseItem("NUGGET_CAST_MIXED_METAL", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_ADVANCED_ALLOY = new BaseItem("NUGGET_CAST_ADVANCED_ALLOY", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_MAG_THOR = new BaseItem("NUGGET_CAST_MAG_THOR", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_SCRAP = new BaseItem("NUGGET_CAST_SCRAP", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_IRIDIUM = new BaseItem("NUGGET_CAST_IRIDIUM", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    // endregion

    // region Blocks
    public static final SlimefunItemStack BLOCK_CAST_REFINED_IRON = new BaseItem("BLOCK_CAST_REFINED_IRON", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_MIXED_METAL = new BaseItem("BLOCK_CAST_MIXED_METAL", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_ADVANCED_ALLOY = new BaseItem("BLOCK_CAST_ADVANCED_ALLOY", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_MAG_THOR = new BaseItem("BLOCK_CAST_MAG_THOR", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_SCRAP = new BaseItem("BLOCK_CAST_SCRAP", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack BLOCK_CAST_IRIDIUM = new BaseItem("BLOCK_CAST_IRIDIUM", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    // endregion

    // endregion

    // region TranscEndance

    // region Nuggets
    public static final SlimefunItemStack NUGGET_CAST_DAXI_STRENGTH = new BaseItem("NUGGET_CAST_DAXI_STRENGTH", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_DAXI_ABSORPTION = new BaseItem("NUGGET_CAST_DAXI_ABSORPTION", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_DAXI_FORTITUDE = new BaseItem("NUGGET_CAST_DAXI_FORTITUDE", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_DAXI_SATURATION = new BaseItem("NUGGET_CAST_DAXI_SATURATION", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    public static final SlimefunItemStack NUGGET_CAST_DAXI_REGENERATION = new BaseItem("NUGGET_CAST_DAXI_REGENERATION", MaterialCompat.safe(XMaterial.IRON_NUGGET));

    // endregion

    // region Ingots

    public static final SlimefunItemStack INGOT_CAST_DAXI_STRENGTH = new BaseItem("INGOT_CAST_DAXI_STRENGTH", MaterialCompat.safe(XMaterial.IRON_INGOT));

    public static final SlimefunItemStack INGOT_CAST_DAXI_ABSORPTION = new BaseItem("INGOT_CAST_DAXI_ABSORPTION", MaterialCompat.safe(XMaterial.IRON_INGOT));

    public static final SlimefunItemStack INGOT_CAST_DAXI_FORTITUDE = new BaseItem("INGOT_CAST_DAXI_FORTITUDE", MaterialCompat.safe(XMaterial.IRON_INGOT));

    public static final SlimefunItemStack INGOT_CAST_DAXI_SATURATION = new BaseItem("INGOT_CAST_DAXI_SATURATION", MaterialCompat.safe(XMaterial.IRON_INGOT));

    public static final SlimefunItemStack INGOT_CAST_DAXI_REGENERATION = new BaseItem("INGOT_CAST_DAXI_REGENERATION", MaterialCompat.safe(XMaterial.IRON_INGOT));

    // endregion

    // endregion

    private static final ItemStack[] RECIPE_GROUT = new ItemStack[]{
        new ItemStack(MaterialCompat.safe(XMaterial.CLAY)), new ItemStack(MaterialCompat.safe(XMaterial.SAND)), new ItemStack(MaterialCompat.safe(XMaterial.SAND)),
        new ItemStack(MaterialCompat.safe(XMaterial.SAND)), new ItemStack(MaterialCompat.safe(XMaterial.SAND)), new ItemStack(MaterialCompat.safe(XMaterial.GRAVEL)),
        new ItemStack(MaterialCompat.safe(XMaterial.GRAVEL)), new ItemStack(MaterialCompat.safe(XMaterial.GRAVEL)), new ItemStack(MaterialCompat.safe(XMaterial.GRAVEL))
    };

    private static final ItemStack[] RECIPE_SEARED_BRICK = new ItemStack[]{
        Materials.GROUT.item(), null, null,
        null, null, null,
        null, null, null
    };

    private static final ItemStack[] RECIPE_SEARED_BRICK_BLOCK = new ItemStack[]{
        Materials.SEARED_BRICK.item(), Materials.SEARED_BRICK.item(), null,
        Materials.SEARED_BRICK.item(), Materials.SEARED_BRICK.item(), null,
        null, null, null
    };

    private static final ItemStack[] RECIPE_SMELTERY_CONTROLLER = new ItemStack[]{
        Materials.SEARED_BRICK.item(), Materials.SEARED_BRICK.item(), Materials.SEARED_BRICK.item(),
        Materials.SEARED_BRICK.item(), null, Materials.SEARED_BRICK.item(),
        Materials.SEARED_BRICK.item(), Materials.SEARED_BRICK.item(), Materials.SEARED_BRICK.item()
    };

    private static final ItemStack[] RECIPE_SMELTERY_SPOUT = new ItemStack[]{
        Materials.SEARED_BRICK.item(), null, Materials.SEARED_BRICK.item(),
        Materials.SEARED_BRICK.item(), null, Materials.SEARED_BRICK.item(),
        Materials.SEARED_BRICK.item(), null, Materials.SEARED_BRICK.item()
    };

    private static final ItemStack[] RECIPE_SMELTERY_TANK = new ItemStack[]{
        Materials.SEARED_BRICK.item(), new ItemStack(MaterialCompat.safe(XMaterial.GLASS)), Materials.SEARED_BRICK.item(),
        Materials.SEARED_BRICK.item(), new ItemStack(MaterialCompat.safe(XMaterial.GLASS)), Materials.SEARED_BRICK.item(),
        Materials.SEARED_BRICK.item(), new ItemStack(MaterialCompat.safe(XMaterial.GLASS)), Materials.SEARED_BRICK.item()
    };

    private static final ItemStack[] RECIPE_REINFORCED_PLATE = new ItemStack[]{
        Materials.BLOCK_CAST_REINFORCED.item(), Materials.BLOCK_CAST_REINFORCED.item(), Materials.BLOCK_CAST_REINFORCED.item(),
        Materials.BLOCK_CAST_REINFORCED.item(), new ItemStack(MaterialCompat.safe(XMaterial.OBSIDIAN)), Materials.BLOCK_CAST_REINFORCED.item(),
        Materials.BLOCK_CAST_REINFORCED.item(), Materials.BLOCK_CAST_REINFORCED.item(), Materials.BLOCK_CAST_REINFORCED.item()
    };

    public static void set(SlimeTinker p) {

        // Vanilla + Core SF + Tinker's General
        new UnplaceableBlock(ItemGroups.MATERIALS, GROUT, Workbench.TYPE, RECIPE_GROUT).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, SEARED_BRICK, RecipeType.SMELTERY, RECIPE_SEARED_BRICK).register(p);
        new TinkersSmeltery(ItemGroups.MATERIALS, SMELTERY_CONTROLLER, Workbench.TYPE, RECIPE_SMELTERY_CONTROLLER).register(p);
        new SlimefunItem(ItemGroups.MATERIALS, SEARED_TANK, Workbench.TYPE, RECIPE_SMELTERY_TANK).register(p);
        new SlimefunItem(ItemGroups.MATERIALS, SPOUT, Workbench.TYPE, RECIPE_SMELTERY_SPOUT).register(p);
        new SlimefunItem(ItemGroups.MATERIALS, SEARED_BRICK_BLOCK, Workbench.TYPE, RECIPE_SEARED_BRICK_BLOCK).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, MOD_PLATE, Workbench.TYPE, RECIPE_REINFORCED_PLATE).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_COPPER, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_COPPER, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_LEAD, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_LEAD, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_SILVER, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_SILVER, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_ALUMINUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_ALUMINUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_MAGNESIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_MAGNESIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_ZINC, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_ZINC, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_TIN, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_TIN, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_STEEL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_STEEL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_DAMASCUS_STEEL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_DAMASCUS_STEEL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_DURALIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_DURALIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_BRONZE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_BRONZE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_ALU_BRONZE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_ALU_BRONZE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_HARD_METAL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_HARD_METAL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_COR_BRONZE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_COR_BRONZE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_SOLDER, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_SOLDER, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_BILLON, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_BILLON, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_BRASS, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_BRASS, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_ALU_BRASS, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_ALU_BRASS, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_NICKEL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_NICKEL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_COBALT, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_COBALT, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_REINFORCED, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_REINFORCED, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_FERROSILICON, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_FERROSILICON, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_REDSTONE_ALLOY, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_REDSTONE_ALLOY, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_BOOMERITE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, INGOT_CAST_BOOMERITE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_INGOT.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_BOOMERITE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_SEFIRITE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, INGOT_CAST_SEFIRITE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_INGOT.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_SEFIRITE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_CRINGLEIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, INGOT_CAST_CRINGLEIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_INGOT.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_CRINGLEIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_FONDNESS, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, INGOT_CAST_FONDNESS, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_INGOT.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_DEVOTION, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, INGOT_CAST_DEVOTION, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_INGOT.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_PASSION, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, INGOT_CAST_PASSION, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_INGOT.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_LOVE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, INGOT_CAST_LOVE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_INGOT.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_LOVE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_NICE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, INGOT_CAST_NICE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_INGOT.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_NICE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_SMITHIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, INGOT_CAST_SMITHIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_INGOT.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_SMITHIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_ANNIVERSARIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, INGOT_CAST_ANNIVERSARIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_INGOT.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_ANNIVERSARIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_REINFORCED_DRACONIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, INGOT_CAST_REINFORCED_DRACONIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_INGOT.item())).register(p);
        new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_REINFORCED_DRACONIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

        new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_COAL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);

        // Infinity Expansion
        if (SupportedPluginsManager.INFINITY_EXPANSION) {
            new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_VOID, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_INFINITY, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_INFINITY, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_MAGSTEEL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_MAGSTEEL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_TITANIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_TITANIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_MYTHRIL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_MYTHRIL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_ADAMANTITE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_ADAMANTITE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_MAGNONIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_MAGNONIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);
        }

        // SFWarfare
        if (SupportedPluginsManager.SLIMEFUN_WARFARE) {
            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_SLIMESTEEL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_SLIMESTEEL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_REINFORCED_SLIMESTEEL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_REINFORCED_SLIMESTEEL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_OSMIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_OSMIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_OSMIUM_SUPER_ALLOY, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_OSMIUM_SUPER_ALLOY, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_UNPATENTABILUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_UNPATENTABILUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);
        }

        // DynaTech
        if (SupportedPluginsManager.DYNATECH) {
            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_STAINLESSSTEEL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_STAINLESSSTEEL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);
        }

        // LiteXpansion
        if (SupportedPluginsManager.LITEXPANSION) {
            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_REFINED_IRON, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_REFINED_IRON, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_MIXED_METAL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_MIXED_METAL, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_ADVANCED_ALLOY, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_ADVANCED_ALLOY, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_MAG_THOR, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_MAG_THOR, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_SCRAP, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_SCRAP, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);

            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_IRIDIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, BLOCK_CAST_IRIDIUM, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_BLOCK.item())).register(p);
        }

        // TranscEndence
        if (SupportedPluginsManager.TRANSCENDENCE) {
            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_DAXI_STRENGTH, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, INGOT_CAST_DAXI_STRENGTH, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_INGOT.item())).register(p);

            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_DAXI_ABSORPTION, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, INGOT_CAST_DAXI_ABSORPTION, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_INGOT.item())).register(p);

            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_DAXI_FORTITUDE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, INGOT_CAST_DAXI_FORTITUDE, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_INGOT.item())).register(p);

            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_DAXI_SATURATION, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, INGOT_CAST_DAXI_SATURATION, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_INGOT.item())).register(p);

            new UnplaceableBlock(ItemGroups.MATERIALS, NUGGET_CAST_DAXI_REGENERATION, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_NUGGET.item())).register(p);
            new UnplaceableBlock(ItemGroups.MATERIALS, INGOT_CAST_DAXI_REGENERATION, DummySmeltery.TYPE, ItemUtils.getMiddleOnlyRecipe(Casts.CAST_INGOT.item())).register(p);
        }
    }
}


