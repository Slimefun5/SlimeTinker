package io.github.sefiraat.slimetinker.items;

import io.github.sefiraat.slimetinker.SlimeTinker;
import io.github.sefiraat.slimetinker.itemgroups.ItemGroups;
import io.github.sefiraat.slimetinker.items.templates.ArmourTemplate;
import io.github.sefiraat.slimetinker.items.templates.ToolTemplate;
import io.github.sefiraat.slimetinker.items.templates.ToolTemplateExplosive;
import io.github.sefiraat.slimetinker.items.workstations.armourtable.DummyArmourTable;
import io.github.sefiraat.slimetinker.items.workstations.tooltable.DummyToolTable;
import io.github.sefiraat.slimetinker.utils.Ids;
import io.github.sefiraat.slimetinker.utils.MaterialCompat;
import io.github.sefiraat.slimetinker.utils.SkullTextures;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Guide {

    private Guide() {
        throw new UnsupportedOperationException("Utility Class");
    }

    public static final String DESC_ERROR = "Error";
    public static final int LEVEL_TOOLS_NETHERITE = 10;
    public static final int LEVEL_TOOLS_DIAMOND = 8;
    public static final int LEVEL_TOOLS_IRON = 6;
    public static final int LEVEL_TOOLS_GOLD = 3;

    public static final int LEVEL_ARMOUR_NETHERITE = 10;
    public static final int LEVEL_ARMOUR_DIAMOND = 8;
    public static final int LEVEL_ARMOUR_IRON = 6;
    public static final int LEVEL_ARMOUR_CHAIN = 3;

    public static final SlimefunItemStack TOOL_SHOVEL_DUMMY = new BaseItem("TOOL_SHOVEL_DUMMY", MaterialCompat.safe(XMaterial.STONE_SHOVEL));

    public static final SlimefunItemStack TOOL_PICKAXE_DUMMY = new BaseItem("TOOL_PICKAXE_DUMMY", MaterialCompat.safe(XMaterial.STONE_PICKAXE));

    public static final SlimefunItemStack TOOL_AXE_DUMMY = new BaseItem("TOOL_AXE_DUMMY", MaterialCompat.safe(XMaterial.STONE_AXE));

    public static final SlimefunItemStack TOOL_HOE_DUMMY = new BaseItem("TOOL_HOE_DUMMY", MaterialCompat.safe(XMaterial.STONE_HOE));

    public static final SlimefunItemStack TOOL_SWORD_DUMMY = new BaseItem("TOOL_SWORD_DUMMY", MaterialCompat.safe(XMaterial.STONE_SWORD));

    public static final SlimefunItemStack ARMOUR_HELMET_DUMMY = new BaseItem("ARMOUR_HELMET_DUMMY", MaterialCompat.safe(XMaterial.LEATHER_HELMET));

    public static final SlimefunItemStack ARMOUR_CHESTPLATE_DUMMY = new BaseItem("ARMOUR_CHESTPLATE_DUMMY", MaterialCompat.safe(XMaterial.LEATHER_CHESTPLATE));

    public static final SlimefunItemStack ARMOUR_LEGGINGS_DUMMY = new BaseItem("ARMOUR_LEGGINGS_DUMMY", MaterialCompat.safe(XMaterial.LEATHER_LEGGINGS));

    public static final SlimefunItemStack ARMOUR_BOOTS_DUMMY = new BaseItem("ARMOUR_BOOTS_DUMMY", MaterialCompat.safe(XMaterial.LEATHER_BOOTS));

    public static final SlimefunItemStack TOOL_AXE = new BaseItem("TOOL_AXE", SkullTextures.PART_AXE_HEAD);

    public static final SlimefunItemStack TOOL_HOE = new BaseItem("TOOL_HOE", SkullTextures.PART_HOE_HEAD);

    public static final SlimefunItemStack TOOL_PICKAXE = new BaseItem("TOOL_PICKAXE", SkullTextures.PART_PICKAXE_HEAD);

    public static final SlimefunItemStack TOOL_SHOVEL = new BaseItem("TOOL_SHOVEL", SkullTextures.PART_SHOVEL_HEAD);

    public static final SlimefunItemStack TOOL_SWORD = new BaseItem("TOOL_SWORD", SkullTextures.PART_SWORD_BLADE);

    public static final SlimefunItemStack TOOL_AXE_EXP = new BaseItem("TOOL_AXE_EXP", SkullTextures.PART_AXE_HEAD);

    public static final SlimefunItemStack TOOL_HOE_EXP = new BaseItem("TOOL_HOE_EXP", SkullTextures.PART_HOE_HEAD);

    public static final SlimefunItemStack TOOL_PICKAXE_EXP = new BaseItem("TOOL_PICKAXE_EXP", SkullTextures.PART_PICKAXE_HEAD);

    public static final SlimefunItemStack TOOL_SHOVEL_EXP = new BaseItem("TOOL_SHOVEL_EXP", SkullTextures.PART_SHOVEL_HEAD);

    public static final SlimefunItemStack TOOL_SWORD_EXP = new BaseItem("TOOL_SWORD_EXP", SkullTextures.PART_SWORD_BLADE);

    public static final SlimefunItemStack ARMOUR_HELM = new BaseItem("ARMOUR_HELM", SkullTextures.PART_HELM_PLATES);

    public static final SlimefunItemStack ARMOUR_CHESTPLATE = new BaseItem("ARMOUR_CHESTPLATE", SkullTextures.PART_CHEST_PLATES);

    public static final SlimefunItemStack ARMOUR_LEGGINGS = new BaseItem("ARMOUR_LEGGINGS", SkullTextures.PART_LEG_PLATES);

    public static final SlimefunItemStack ARMOUR_BOOTS = new BaseItem("ARMOUR_BOOTS", SkullTextures.PART_BOOTS_PLATES);

    public static final SlimefunItemStack TOOL_INFO = new BaseItem("TOOL_INFO", SkullTextures.INFO);

    public static final SlimefunItemStack ARMOUR_INFO = new BaseItem("ARMOUR_INFO", SkullTextures.INFO);

    public static final ToolTemplate SHOVEL = new ToolTemplate(ItemGroups.DUMMY, TOOL_SHOVEL, DummyToolTable.TYPE, new ItemStack[9]);
    public static final ToolTemplate PICKAXE = new ToolTemplate(ItemGroups.DUMMY, TOOL_PICKAXE, DummyToolTable.TYPE, new ItemStack[9]);
    public static final ToolTemplate AXE = new ToolTemplate(ItemGroups.DUMMY, TOOL_AXE, DummyToolTable.TYPE, new ItemStack[9]);
    public static final ToolTemplate HOE = new ToolTemplate(ItemGroups.DUMMY, TOOL_HOE, DummyToolTable.TYPE, new ItemStack[9]);
    public static final ToolTemplate SWORD = new ToolTemplate(ItemGroups.DUMMY, TOOL_SWORD, DummyToolTable.TYPE, new ItemStack[9]);
    public static final ToolTemplateExplosive EXP_SHOVEL = new ToolTemplateExplosive(ItemGroups.DUMMY, TOOL_SHOVEL_EXP, DummyToolTable.TYPE, new ItemStack[9]);
    public static final ToolTemplateExplosive EXP_PICKAXE = new ToolTemplateExplosive(ItemGroups.DUMMY, TOOL_PICKAXE_EXP, DummyToolTable.TYPE, new ItemStack[9]);
    public static final ToolTemplateExplosive EXP_AXE = new ToolTemplateExplosive(ItemGroups.DUMMY, TOOL_AXE_EXP, DummyToolTable.TYPE, new ItemStack[9]);
    public static final ToolTemplateExplosive EXP_HOE = new ToolTemplateExplosive(ItemGroups.DUMMY, TOOL_HOE_EXP, DummyToolTable.TYPE, new ItemStack[9]);
    public static final ToolTemplateExplosive EXP_SWORD = new ToolTemplateExplosive(ItemGroups.DUMMY, TOOL_SWORD_EXP, DummyToolTable.TYPE, new ItemStack[9]);
    public static final ArmourTemplate HELM = new ArmourTemplate(ItemGroups.DUMMY, ARMOUR_HELM, DummyToolTable.TYPE, new ItemStack[9]);
    public static final ArmourTemplate CHEST = new ArmourTemplate(ItemGroups.DUMMY, ARMOUR_CHESTPLATE, DummyToolTable.TYPE, new ItemStack[9]);
    public static final ArmourTemplate LEG = new ArmourTemplate(ItemGroups.DUMMY, ARMOUR_LEGGINGS, DummyToolTable.TYPE, new ItemStack[9]);
    public static final ArmourTemplate BOOT = new ArmourTemplate(ItemGroups.DUMMY, ARMOUR_BOOTS, DummyToolTable.TYPE, new ItemStack[9]);

    private static final Map<String, Map<Integer, Material>> GROWTH_MAP = new HashMap<>();

    public static Map<String, Map<Integer, Material>> getGrowthMap() {
        return GROWTH_MAP;
    }

    @Nonnull
    private static Map<Integer, Material> getShovelMap() {
        Map<Integer, Material> map = new LinkedHashMap<>();
        map.put(LEVEL_TOOLS_NETHERITE, MaterialCompat.safe(XMaterial.NETHERITE_SHOVEL));
        map.put(LEVEL_TOOLS_DIAMOND, MaterialCompat.safe(XMaterial.DIAMOND_SHOVEL));
        map.put(LEVEL_TOOLS_IRON, MaterialCompat.safe(XMaterial.IRON_SHOVEL));
        map.put(LEVEL_TOOLS_GOLD, MaterialCompat.safe(XMaterial.GOLDEN_SHOVEL));
        return map;
    }

    @Nonnull
    private static Map<Integer, Material> getPickaxeMap() {
        Map<Integer, Material> map = new LinkedHashMap<>();
        map.put(LEVEL_TOOLS_NETHERITE, MaterialCompat.safe(XMaterial.NETHERITE_PICKAXE));
        map.put(LEVEL_TOOLS_DIAMOND, MaterialCompat.safe(XMaterial.DIAMOND_PICKAXE));
        map.put(LEVEL_TOOLS_IRON, MaterialCompat.safe(XMaterial.IRON_PICKAXE));
        map.put(LEVEL_TOOLS_GOLD, MaterialCompat.safe(XMaterial.GOLDEN_PICKAXE));
        return map;
    }

    @Nonnull
    private static Map<Integer, Material> getAxeMap() {
        Map<Integer, Material> map = new LinkedHashMap<>();
        map.put(LEVEL_TOOLS_NETHERITE, MaterialCompat.safe(XMaterial.NETHERITE_AXE));
        map.put(LEVEL_TOOLS_DIAMOND, MaterialCompat.safe(XMaterial.DIAMOND_AXE));
        map.put(LEVEL_TOOLS_IRON, MaterialCompat.safe(XMaterial.IRON_AXE));
        map.put(LEVEL_TOOLS_GOLD, MaterialCompat.safe(XMaterial.GOLDEN_AXE));
        return map;
    }

    @Nonnull
    private static Map<Integer, Material> getHoeMap() {
        Map<Integer, Material> map = new LinkedHashMap<>();
        map.put(LEVEL_TOOLS_NETHERITE, MaterialCompat.safe(XMaterial.NETHERITE_HOE));
        map.put(LEVEL_TOOLS_DIAMOND, MaterialCompat.safe(XMaterial.DIAMOND_HOE));
        map.put(LEVEL_TOOLS_IRON, MaterialCompat.safe(XMaterial.IRON_HOE));
        map.put(LEVEL_TOOLS_GOLD, MaterialCompat.safe(XMaterial.GOLDEN_HOE));
        return map;
    }

    @Nonnull
    private static Map<Integer, Material> getSwordMap() {
        Map<Integer, Material> map = new LinkedHashMap<>();
        map.put(LEVEL_TOOLS_NETHERITE, MaterialCompat.safe(XMaterial.NETHERITE_SWORD));
        map.put(LEVEL_TOOLS_DIAMOND, MaterialCompat.safe(XMaterial.DIAMOND_SWORD));
        map.put(LEVEL_TOOLS_IRON, MaterialCompat.safe(XMaterial.IRON_SWORD));
        map.put(LEVEL_TOOLS_GOLD, MaterialCompat.safe(XMaterial.GOLDEN_SWORD));
        return map;
    }

    @Nonnull
    private static Map<Integer, Material> getHelmetMap() {
        Map<Integer, Material> map = new LinkedHashMap<>();
        map.put(LEVEL_ARMOUR_NETHERITE, MaterialCompat.safe(XMaterial.NETHERITE_HELMET));
        map.put(LEVEL_ARMOUR_DIAMOND, MaterialCompat.safe(XMaterial.DIAMOND_HELMET));
        map.put(LEVEL_ARMOUR_IRON, MaterialCompat.safe(XMaterial.IRON_HELMET));
        map.put(LEVEL_ARMOUR_CHAIN, MaterialCompat.safe(XMaterial.CHAINMAIL_HELMET));
        return map;
    }

    @Nonnull
    private static Map<Integer, Material> getChestplateMap() {
        Map<Integer, Material> map = new LinkedHashMap<>();
        map.put(LEVEL_ARMOUR_NETHERITE, MaterialCompat.safe(XMaterial.NETHERITE_CHESTPLATE));
        map.put(LEVEL_ARMOUR_DIAMOND, MaterialCompat.safe(XMaterial.DIAMOND_CHESTPLATE));
        map.put(LEVEL_ARMOUR_IRON, MaterialCompat.safe(XMaterial.IRON_CHESTPLATE));
        map.put(LEVEL_ARMOUR_CHAIN, MaterialCompat.safe(XMaterial.CHAINMAIL_CHESTPLATE));
        return map;
    }

    @Nonnull
    private static Map<Integer, Material> getLeggingsMap() {
        Map<Integer, Material> map = new LinkedHashMap<>();
        map.put(LEVEL_ARMOUR_NETHERITE, MaterialCompat.safe(XMaterial.NETHERITE_LEGGINGS));
        map.put(LEVEL_ARMOUR_DIAMOND, MaterialCompat.safe(XMaterial.DIAMOND_LEGGINGS));
        map.put(LEVEL_ARMOUR_IRON, MaterialCompat.safe(XMaterial.IRON_LEGGINGS));
        map.put(LEVEL_ARMOUR_CHAIN, MaterialCompat.safe(XMaterial.CHAINMAIL_LEGGINGS));
        return map;
    }

    @Nonnull
    private static Map<Integer, Material> getBootsMap() {
        Map<Integer, Material> map = new LinkedHashMap<>();
        map.put(LEVEL_ARMOUR_NETHERITE, MaterialCompat.safe(XMaterial.NETHERITE_BOOTS));
        map.put(LEVEL_ARMOUR_DIAMOND, MaterialCompat.safe(XMaterial.DIAMOND_BOOTS));
        map.put(LEVEL_ARMOUR_IRON, MaterialCompat.safe(XMaterial.IRON_BOOTS));
        map.put(LEVEL_ARMOUR_CHAIN, MaterialCompat.safe(XMaterial.CHAINMAIL_BOOTS));
        return map;
    }

    @Nonnull
    private static ItemStack[] getDummyToolRecipe(ItemStack i3) {
        return new ItemStack[]{
            null, null, null,
            Parts.PART_TOOL_ROD_DUMMY.item(), Parts.PART_BINDING_DUMMY.item(), i3,
            null, null, null
        };
    }

    @Nonnull
    private static ItemStack[] getDummyArmourRecipe(ItemStack i3) {
        return new ItemStack[]{
            null, null, null,
            i3, Parts.PART_GAMBESON_DUMMY.item(), Parts.PART_MAIL_LINKS_DUMMY.item(),
            null, null, null
        };
    }

    public static void set(SlimeTinker p) {
        new UnplaceableBlock(ItemGroups.TOOLS, TOOL_INFO, DummyToolTable.TYPE, new ItemStack[9]).register(p);
        new UnplaceableBlock(ItemGroups.TOOLS, TOOL_SHOVEL_DUMMY, DummyToolTable.TYPE, getDummyToolRecipe(Parts.PART_SHOVEL_HEAD_DUMMY.item())).register(p);
        new UnplaceableBlock(ItemGroups.TOOLS, TOOL_PICKAXE_DUMMY, DummyToolTable.TYPE, getDummyToolRecipe(Parts.PART_PICKAXE_HEAD_DUMMY.item())).register(p);
        new UnplaceableBlock(ItemGroups.TOOLS, TOOL_AXE_DUMMY, DummyToolTable.TYPE, getDummyToolRecipe(Parts.PART_AXE_HEAD_DUMMY.item())).register(p);
        new UnplaceableBlock(ItemGroups.TOOLS, TOOL_HOE_DUMMY, DummyToolTable.TYPE, getDummyToolRecipe(Parts.PART_HOE_HEAD_DUMMY.item())).register(p);
        new UnplaceableBlock(ItemGroups.TOOLS, TOOL_SWORD_DUMMY, DummyToolTable.TYPE, getDummyToolRecipe(Parts.PART_SWORD_BLADE_DUMMY.item())).register(p);

        new UnplaceableBlock(ItemGroups.ARMOUR, ARMOUR_INFO, DummyArmourTable.TYPE, new ItemStack[9]).register(p);
        new UnplaceableBlock(ItemGroups.ARMOUR, ARMOUR_HELMET_DUMMY, DummyArmourTable.TYPE, getDummyArmourRecipe(Parts.PART_HELM_PLATE_DUMMY.item())).register(p);
        new UnplaceableBlock(ItemGroups.ARMOUR, ARMOUR_CHESTPLATE_DUMMY, DummyArmourTable.TYPE, getDummyArmourRecipe(Parts.PART_CHEST_PLATE_DUMMY.item())).register(p);
        new UnplaceableBlock(ItemGroups.ARMOUR, ARMOUR_LEGGINGS_DUMMY, DummyArmourTable.TYPE, getDummyArmourRecipe(Parts.PART_LEG_PLATE_DUMMY.item())).register(p);
        new UnplaceableBlock(ItemGroups.ARMOUR, ARMOUR_BOOTS_DUMMY, DummyArmourTable.TYPE, getDummyArmourRecipe(Parts.PART_BOOT_PLATE_DUMMY.item())).register(p);

        SHOVEL.register(p);
        PICKAXE.register(p);
        AXE.register(p);
        HOE.register(p);
        SWORD.register(p);
        EXP_SHOVEL.register(p);
        EXP_PICKAXE.register(p);
        EXP_AXE.register(p);
        EXP_HOE.register(p);
        EXP_SWORD.register(p);

        HELM.register(p);
        CHEST.register(p);
        LEG.register(p);
        BOOT.register(p);

        GROWTH_MAP.put(Ids.SHOVEL, getShovelMap());
        GROWTH_MAP.put(Ids.PICKAXE, getPickaxeMap());
        GROWTH_MAP.put(Ids.AXE, getAxeMap());
        GROWTH_MAP.put(Ids.HOE, getHoeMap());
        GROWTH_MAP.put(Ids.SWORD, getSwordMap());

        GROWTH_MAP.put(Ids.HELMET, getHelmetMap());
        GROWTH_MAP.put(Ids.CHESTPLATE, getChestplateMap());
        GROWTH_MAP.put(Ids.LEGGINGS, getLeggingsMap());
        GROWTH_MAP.put(Ids.BOOTS, getBootsMap());
    }
}



