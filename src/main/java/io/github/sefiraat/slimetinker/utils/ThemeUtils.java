package io.github.sefiraat.slimetinker.utils;

import io.github.sefiraat.slimetinker.items.BaseItem;
import io.github.sefiraat.slimetinker.utils.enums.ThemeItemType;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
public final class ThemeUtils {

    private ThemeUtils() {
        throw new UnsupportedOperationException("Utility Class");
    }

    public static final ChatColor WARNING = ChatColor.YELLOW;
    public static final ChatColor ERROR = ChatColor.RED;
    public static final ChatColor NOTICE = ChatColor.WHITE;
    public static final ChatColor PASSIVE = ChatColor.GRAY;
    public static final ChatColor SUCCESS = ChatColor.GREEN;

    public static final ChatColor MAIN = ColorCompat.of("#21588f");
    public static final ChatColor GUI_HEAD = ColorCompat.of("#03fcdf");
    public static final ChatColor CLICK_INFO = ColorCompat.of("#e4ed32");
    public static final ChatColor ITEM_TYPEDESC = ColorCompat.of("#f0ea4f");
    public static final ChatColor ITEM_CRAFTING = ColorCompat.of("#dbcea9");
    public static final ChatColor ITEM_MACHINE = ColorCompat.of("#3295a8");
    public static final ChatColor ITEM_RARE_DROP = ColorCompat.of("#bf307f");
    public static final ChatColor ITEM_BASE = ColorCompat.of("#9e9e9e");
    public static final ChatColor ITEM_CHEST = ColorCompat.of("#b89b1c");
    public static final ChatColor ITEM_MOLTEN_METAL = ColorCompat.of("#a60a0a");
    public static final ChatColor ITEM_LIQUID = ColorCompat.of("#65dbb4");
    public static final ChatColor ITEM_CAST = ColorCompat.of("#ffe138");
    public static final ChatColor ITEM_PART = ColorCompat.of("#42c8f5");
    public static final ChatColor ITEM_TOOL = ColorCompat.of("#c2fc03");
    public static final ChatColor ITEM_ARMOUR = ColorCompat.of("#c2fc03");
    public static final ChatColor ITEM_INFO = ColorCompat.of("#21588f");
    public static final ChatColor ITEM_MOD = ColorCompat.of("#bf307f");
    public static final ChatColor ITEM_PROP = ColorCompat.of("#bf307f");
    public static final ChatColor ITEM_MULTIBLOCK = ColorCompat.of("#3295a8");
    public static final ChatColor ADD_INFINITY = ColorCompat.of("#7ecee0");
    public static final ChatColor ADD_SLIMEFUN_WARFARE = ColorCompat.of("#c1db4d");
    public static final ChatColor ADD_DYNATECH = ColorCompat.of("#60d1cd");
    public static final ChatColor ADD_LITEXPANSION = ColorCompat.of("#8a8a8a");
    public static final ChatColor ADD_TRANSCENDENCE = ColorCompat.of("#c242f5");
    public static final ChatColor ADD_NETWORKS = ColorCompat.of("#05ad08");
    public static final ChatColor GUIDE = ColorCompat.of("#444444");

    public static final String LORE_TYPE_CRAFT = ITEM_TYPEDESC + "Crafting Material";
    public static final String LORE_TYPE_MACHINE = ITEM_TYPEDESC + "Machine";
    public static final String LORE_TYPE_DROP = ITEM_TYPEDESC + "Drop";
    public static final String LORE_TYPE_BASE = ITEM_TYPEDESC + "Base Resource";
    public static final String LORE_TYPE_CHEST = ITEM_TYPEDESC + "Chest";
    public static final String LORE_MOLTEN_METAL = ITEM_TYPEDESC + "Molten Metal";
    public static final String LORE_LIQUID = ITEM_TYPEDESC + "TinkerMaterial";
    public static final String LORE_CAST = ITEM_TYPEDESC + "Cast";
    public static final String LORE_PART = ITEM_TYPEDESC + "Part";
    public static final String LORE_TOOL = ITEM_TYPEDESC + "Tinker's Tool";
    public static final String LORE_ARMOUR = ITEM_TYPEDESC + "Tinker's Armour";
    public static final String LORE_INFO = ITEM_TYPEDESC + "Info";
    public static final String LORE_MOD = ITEM_TYPEDESC + "Modification";
    public static final String LORE_PROP = ITEM_TYPEDESC + "Material Trait";
    public static final String LORE_MULTIBLOCK = ITEM_TYPEDESC + "Mutliblock";

    /**
     * @implNote Slimefun core no longer bakes a placeholder lore line onto fresh item templates (name/lore
     *           are resolved per-viewer at render time), so a brand-new {@link ItemMeta} has no lore
     *           component at all and {@link ItemMeta#getLore()} returns null instead of an empty list.
     */
    @Nonnull
    private static List<String> mutableLore(@Nonnull ItemMeta meta) {
        List<String> lore = meta.getLore();
        return lore != null ? lore : new ArrayList<>();
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static SlimefunItemStack themedItemStack(String id,
                                                    String skull,
                                                    ThemeItemType type,
                                                    String name,
                                                    String... loreLines
    ) {
        BaseItem itemStack = new BaseItem(
            id,
            skull,
            itemTypeColor(type) + name,
            ""
        );
        ItemMeta im = itemStack.getItemMeta();
        List<String> lore = mutableLore(im);
        for (String s : loreLines) {
            lore.add(ThemeUtils.PASSIVE + s);
        }
        lore.add("");
        lore.add(itemTypeDescriptor(type));
        im.setLore(lore);
        itemStack.setItemMeta(im);
        return itemStack;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static SlimefunItemStack themedItemStack(String id,
                                                    Material material,
                                                    ThemeItemType type,
                                                    String name,
                                                    String... loreLines
    ) {
        BaseItem itemStack = new BaseItem(
            id,
            material,
            itemTypeColor(type) + name,
            ""
        );
        ItemMeta im = itemStack.getItemMeta();
        List<String> lore = mutableLore(im);
        for (String s : loreLines) {
            lore.add(ThemeUtils.PASSIVE + s);
        }
        lore.add("");
        lore.add(itemTypeDescriptor(type));
        im.setLore(lore);
        itemStack.setItemMeta(im);
        return itemStack;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static SlimefunItemStack themedItemStack(String id,
                                                    String skull,
                                                    ThemeItemType type,
                                                    String name,
                                                    List<String> loreLines
    ) {
        BaseItem itemStack = new BaseItem(
            id,
            skull,
            itemTypeColor(type) + name,
            ""
        );
        ItemMeta im = itemStack.getItemMeta();
        List<String> lore = mutableLore(im);
        for (String s : loreLines) {
            lore.add(ThemeUtils.PASSIVE + s);
        }
        lore.add("");
        lore.add(itemTypeDescriptor(type));
        im.setLore(lore);
        itemStack.setItemMeta(im);
        return itemStack;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static SlimefunItemStack themedItemStack(String id,
                                                    Material material,
                                                    ThemeItemType type,
                                                    String name,
                                                    List<String> loreLines
    ) {
        BaseItem itemStack = new BaseItem(
            id,
            material,
            itemTypeColor(type) + name,
            ""
        );
        ItemMeta im = itemStack.getItemMeta();
        List<String> lore = mutableLore(im);
        for (String s : loreLines) {
            lore.add(ThemeUtils.PASSIVE + s);
        }
        lore.add("");
        lore.add(itemTypeDescriptor(type));
        im.setLore(lore);
        itemStack.setItemMeta(im);
        return itemStack;
    }

    @Nonnull
    public static ChatColor itemTypeColor(@Nonnull ThemeItemType type) {
        switch (type) {
            case DROP:
                return ITEM_RARE_DROP;
            case CHEST:
                return ITEM_CHEST;
            case MACHINE:
                return ITEM_MACHINE;
            case CRAFTING:
                return ITEM_CRAFTING;
            case MOLTEN_METAL:
                return ITEM_MOLTEN_METAL;
            case LIQUID:
                return ITEM_LIQUID;
            case CAST:
                return ITEM_CAST;
            case PART:
                return ITEM_PART;
            case TOOL:
                return ITEM_TOOL;
            case ARMOUR:
                return ITEM_ARMOUR;
            case INFO:
                return ITEM_INFO;
            case MOD:
                return ITEM_MOD;
            case PROP:
                return ITEM_PROP;
            case MULT:
                return ITEM_MULTIBLOCK;
            default:
                return ITEM_BASE;
        }
    }

    @Nonnull
    public static String itemTypeDescriptor(@Nonnull ThemeItemType type) {
        switch (type) {
            case DROP:
                return LORE_TYPE_DROP;
            case CHEST:
                return LORE_TYPE_CHEST;
            case MACHINE:
                return LORE_TYPE_MACHINE;
            case CRAFTING:
                return LORE_TYPE_CRAFT;
            case MOLTEN_METAL:
                return LORE_MOLTEN_METAL;
            case LIQUID:
                return LORE_LIQUID;
            case CAST:
                return LORE_CAST;
            case PART:
                return LORE_PART;
            case TOOL:
                return LORE_TOOL;
            case ARMOUR:
                return LORE_ARMOUR;
            case INFO:
                return LORE_INFO;
            case MOD:
                return LORE_MOD;
            case PROP:
                return LORE_PROP;
            case MULT:
                return LORE_MULTIBLOCK;
            default:
                return LORE_TYPE_BASE;
        }
    }

    @Nonnull
    public static String toTitleCase(@Nonnull String string) {
        return toTitleCase(string, true);
    }

    @Nonnull
    public static String toTitleCase(@Nonnull String string, boolean delimiterToSpace) {
        return toTitleCase(string, delimiterToSpace, " _'-/");
    }

    @Nonnull
    public static String toTitleCase(@Nonnull String string, boolean delimiterToSpace, @Nonnull String delimiters) {
        final StringBuilder builder = new StringBuilder();
        boolean capNext = true;

        for (char character : string.toCharArray()) {
            character = (capNext) ? Character.toUpperCase(character) : Character.toLowerCase(character);
            builder.append(character);
            capNext = (delimiters.indexOf(character) >= 0);
        }

        String built = builder.toString();

        if (delimiterToSpace) {
            final char space = ' ';
            for (char c : delimiters.toCharArray()) {
                built = built.replace(c, space);
            }
        }
        return built;
    }

    @Nonnull
    public static String getLine() {
        return PASSIVE + new String(new char[25]).replace('\0', '-');
    }
}

