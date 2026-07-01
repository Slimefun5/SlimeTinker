package io.github.sefiraat.slimetinker.modifiers;

import io.github.mooy1.infinitylib.common.StackUtils;
import io.github.sefiraat.slimetinker.items.Materials;
import io.github.sefiraat.slimetinker.utils.ItemUtils;
import io.github.sefiraat.slimetinker.utils.Keys;
import io.github.sefiraat.slimetinker.utils.MaterialCompat;
import io.github.sefiraat.slimetinker.compat.Pdc;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Modifications {

    private static final List<String> MODIFICATION_LIST_TOOL = new LinkedList<>();
    private static final Map<String, Mod> MODIFICATION_DEFINITIONS_TOOL = new HashMap<>();
    private static final Map<Integer, Integer> MOD_MAP_REDSTONE_TOOL = new HashMap<>();
    private static final Map<Integer, Integer> MOD_MAP_LAPIS_TOOL = new HashMap<>();
    private static final Map<Integer, Integer> MOD_MAP_QUARTZ_TOOL = new HashMap<>();
    private static final Map<Integer, Integer> MOD_MAP_DIAMOND_TOOL = new HashMap<>();
    private static final Map<Integer, Integer> MOD_MAP_EMERALD_TOOL = new HashMap<>();
    private static final Map<Integer, Integer> MOD_MAP_PLATE = new HashMap<>();
    private static final List<String> MODIFICATION_LIST_ARMOUR = new LinkedList<>();
    private static final Map<String, Mod> MODIFICATION_DEFINITIONS_ARMOUR = new HashMap<>();

    static {

        // MOD MAPS
        MOD_MAP_REDSTONE_TOOL.put(1, 128);
        MOD_MAP_REDSTONE_TOOL.put(2, 256);
        MOD_MAP_REDSTONE_TOOL.put(3, 384);
        MOD_MAP_REDSTONE_TOOL.put(4, 512);
        MOD_MAP_REDSTONE_TOOL.put(5, 1024);

        MOD_MAP_LAPIS_TOOL.put(1, 128);
        MOD_MAP_LAPIS_TOOL.put(2, 256);
        MOD_MAP_LAPIS_TOOL.put(3, 384);
        MOD_MAP_LAPIS_TOOL.put(4, 512);
        MOD_MAP_LAPIS_TOOL.put(5, 1024);

        MOD_MAP_QUARTZ_TOOL.put(1, 128);
        MOD_MAP_QUARTZ_TOOL.put(2, 256);
        MOD_MAP_QUARTZ_TOOL.put(3, 384);
        MOD_MAP_QUARTZ_TOOL.put(4, 512);
        MOD_MAP_QUARTZ_TOOL.put(5, 1024);

        MOD_MAP_DIAMOND_TOOL.put(1, 64);
        MOD_MAP_DIAMOND_TOOL.put(2, 128);
        MOD_MAP_DIAMOND_TOOL.put(3, 256);
        MOD_MAP_DIAMOND_TOOL.put(4, 512);

        MOD_MAP_EMERALD_TOOL.put(1, 256);
        MOD_MAP_EMERALD_TOOL.put(2, 512);
        MOD_MAP_EMERALD_TOOL.put(3, 1024);
        MOD_MAP_EMERALD_TOOL.put(4, 2048);

        MOD_MAP_PLATE.put(1, 1);
        MOD_MAP_PLATE.put(2, 1);
        MOD_MAP_PLATE.put(3, 1);
        MOD_MAP_PLATE.put(4, 1);
        MOD_MAP_PLATE.put(5, 1);
        MOD_MAP_PLATE.put(6, 1);
        MOD_MAP_PLATE.put(7, 1);
        MOD_MAP_PLATE.put(8, 1);
        MOD_MAP_PLATE.put(9, 1);
        MOD_MAP_PLATE.put(10, 1);

        // ALL NEW MODS MUST BE PLACED AFTER EXISTING ONES

        // TOOLS

        MODIFICATION_LIST_TOOL.add(StackUtils.getIdOrType(new ItemStack(MaterialCompat.safe(XMaterial.REDSTONE))));
        MODIFICATION_LIST_TOOL.add(StackUtils.getIdOrType(new ItemStack(MaterialCompat.safe(XMaterial.LAPIS_LAZULI))));
        MODIFICATION_LIST_TOOL.add(StackUtils.getIdOrType(new ItemStack(MaterialCompat.safe(XMaterial.QUARTZ))));
        MODIFICATION_LIST_TOOL.add(StackUtils.getIdOrType(new ItemStack(MaterialCompat.safe(XMaterial.DIAMOND))));
        MODIFICATION_LIST_TOOL.add(StackUtils.getIdOrType(new ItemStack(MaterialCompat.safe(XMaterial.EMERALD))));
        MODIFICATION_LIST_TOOL.add(StackUtils.getIdOrType(Materials.MOD_PLATE.item()));

        MODIFICATION_DEFINITIONS_TOOL.put(StackUtils.getIdOrType(new ItemStack(MaterialCompat.safe(XMaterial.REDSTONE))), new Mod(MOD_MAP_REDSTONE_TOOL, Keys.ST_MOD_LEVEL_REDSTONE));
        MODIFICATION_DEFINITIONS_TOOL.put(StackUtils.getIdOrType(new ItemStack(MaterialCompat.safe(XMaterial.LAPIS_LAZULI))), new Mod(MOD_MAP_LAPIS_TOOL, Keys.ST_MOD_LEVEL_LAPIS));
        MODIFICATION_DEFINITIONS_TOOL.put(StackUtils.getIdOrType(new ItemStack(MaterialCompat.safe(XMaterial.QUARTZ))), new Mod(MOD_MAP_QUARTZ_TOOL, Keys.ST_MOD_LEVEL_QUARTZ));
        MODIFICATION_DEFINITIONS_TOOL.put(StackUtils.getIdOrType(new ItemStack(MaterialCompat.safe(XMaterial.DIAMOND))), new Mod(MOD_MAP_DIAMOND_TOOL, Keys.ST_MOD_LEVEL_DIAMOND));
        MODIFICATION_DEFINITIONS_TOOL.put(StackUtils.getIdOrType(new ItemStack(MaterialCompat.safe(XMaterial.EMERALD))), new Mod(MOD_MAP_EMERALD_TOOL, Keys.ST_MOD_LEVEL_EMERALD));
        MODIFICATION_DEFINITIONS_TOOL.put(StackUtils.getIdOrType(Materials.MOD_PLATE.item()), new Mod(MOD_MAP_PLATE, Keys.ST_MOD_LEVEL_OBSIDIAN));

        // ARMOUR

        MODIFICATION_LIST_ARMOUR.add(StackUtils.getIdOrType(Materials.MOD_PLATE.item()));

        MODIFICATION_DEFINITIONS_ARMOUR.put(StackUtils.getIdOrType(Materials.MOD_PLATE.item()), new Mod(MOD_MAP_PLATE, Keys.ST_MOD_LEVEL_OBSIDIAN));

    }

    private Modifications() {
        throw new IllegalStateException("Utility class");
    }

    // PersistentDataAPI has no version-safe int[] overload, so the mod-level array is stored as a String.
    private static String intArrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    private static int[] stringToIntArray(String value) {
        if (value == null || value.isEmpty()) {
            return new int[0];
        }
        String[] parts = value.split(",");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        return arr;
    }

    public static void setModificationMapTool(ItemMeta im, Map<String, Integer> map) {
        int[] mapArray = new int[MODIFICATION_LIST_TOOL.size()];
        for (int i = 0; i < MODIFICATION_LIST_TOOL.size(); i++) {
            mapArray[i] = map.get(MODIFICATION_LIST_TOOL.get(i));
        }
        Pdc.setString(im, Keys.ST_MODS.toString(), intArrayToString(mapArray));
    }

    public static void setModificationMapArmour(ItemMeta im, Map<String, Integer> map) {
        int[] mapArray = new int[MODIFICATION_LIST_ARMOUR.size()];
        for (int i = 0; i < MODIFICATION_LIST_ARMOUR.size(); i++) {
            mapArray[i] = map.get(MODIFICATION_LIST_ARMOUR.get(i));
        }
        Pdc.setString(im, Keys.ST_MODS.toString(), intArrayToString(mapArray));
    }

    public static Map<String, Integer> getModificationMapTool(ItemStack itemStack) {
        ItemMeta im = itemStack.getItemMeta();
        assert im != null;
        Map<String, Integer> map = getModificationMapTool(im);
        itemStack.setItemMeta(im);
        return map;
    }

    public static Map<String, Integer> getModificationMapArmour(ItemStack itemStack) {
        ItemMeta im = itemStack.getItemMeta();
        assert im != null;
        Map<String, Integer> map = getModificationMapArmour(im);
        itemStack.setItemMeta(im);
        return map;
    }

    public static Map<String, Integer> getModificationMapTool(ItemMeta im) {
        Map<String, Integer> map = new LinkedHashMap<>();
        if (Pdc.hasString(im, Keys.ST_MODS.toString())) {
            int[] mapArray = stringToIntArray(Pdc.getString(im, Keys.ST_MODS.toString(), ""));
            assert mapArray != null;
            for (String m : MODIFICATION_LIST_TOOL) {
                if ((MODIFICATION_LIST_TOOL.indexOf(m) + 1) > mapArray.length) {
                    map.put(m, 0);
                } else {
                    map.put(m, mapArray[MODIFICATION_LIST_TOOL.indexOf(m)]);
                }
            }
        } else {
            for (String m : MODIFICATION_LIST_TOOL) {
                map.put(m, 0);
            }
            setModificationMapTool(im, map);
        }
        return map;
    }

    public static Map<String, Integer> getModificationMapArmour(ItemMeta im) {
        Map<String, Integer> map = new LinkedHashMap<>();
        if (Pdc.hasString(im, Keys.ST_MODS.toString())) {
            int[] mapArray = stringToIntArray(Pdc.getString(im, Keys.ST_MODS.toString(), ""));
            assert mapArray != null;
            for (String m : MODIFICATION_LIST_ARMOUR) {
                if ((MODIFICATION_LIST_ARMOUR.indexOf(m) + 1) > mapArray.length) {
                    map.put(m, 0);
                } else {
                    map.put(m, mapArray[MODIFICATION_LIST_ARMOUR.indexOf(m)]);
                }
            }
        } else {
            for (String m : MODIFICATION_LIST_ARMOUR) {
                map.put(m, 0);
            }
            setModificationMapArmour(im, map);
        }
        return map;
    }

    public static int getModLevel(Mod mod, ItemStack itemStack) {
        return Pdc.getInt(itemStack.getItemMeta(), mod.getLevelKey().toString(), 0);
    }

    public static void setModLevel(Mod mod, ItemMeta im, int level) {
        Pdc.setInt(im, mod.getLevelKey().toString(), level);
    }

    public static Map<String, Integer> getAllModLevels(ItemStack itemStack) {
        Map<String, Integer> map = new HashMap<>();
        if (ItemUtils.isTool(itemStack)) {
            for (String m : MODIFICATION_LIST_TOOL) {
                int level = getModLevel(MODIFICATION_DEFINITIONS_TOOL.get(m), itemStack);
                Integer progress = getModificationMapTool(itemStack).get(m);
                if (level > 0 || (progress != null && progress > 0)) { // Has level or progress towards level
                    map.put(m, level);
                }
            }
        } else if (ItemUtils.isArmour(itemStack)) {
            for (String m : MODIFICATION_LIST_ARMOUR) {
                int level = getModLevel(MODIFICATION_DEFINITIONS_ARMOUR.get(m), itemStack);
                Integer progress = getModificationMapArmour(itemStack).get(m);
                if (level > 0 || (progress != null && progress > 0)) { // Has level or progress towards level
                    map.put(m, level);
                }
            }
        }
        return map;
    }

    public static List<String> getModificationListTool() {
        return MODIFICATION_LIST_TOOL;
    }

    public static Map<String, Mod> getModificationDefinitionsTool() {
        return MODIFICATION_DEFINITIONS_TOOL;
    }

    public static Map<Integer, Integer> getModMapRedstoneTool() {
        return MOD_MAP_REDSTONE_TOOL;
    }

    public static Map<Integer, Integer> getModMapLapisTool() {
        return MOD_MAP_LAPIS_TOOL;
    }

    public static Map<Integer, Integer> getModMapQuartzTool() {
        return MOD_MAP_QUARTZ_TOOL;
    }

    public static Map<Integer, Integer> getModMapDiamondTool() {
        return MOD_MAP_DIAMOND_TOOL;
    }

    public static Map<Integer, Integer> getModMapEmeraldTool() {
        return MOD_MAP_EMERALD_TOOL;
    }

    public static Map<Integer, Integer> getModMapPlate() {
        return MOD_MAP_PLATE;
    }

    public static List<String> getModificationListArmour() {
        return MODIFICATION_LIST_ARMOUR;
    }

    public static Map<String, Mod> getModificationDefinitionsArmour() {
        return MODIFICATION_DEFINITIONS_ARMOUR;
    }
}

