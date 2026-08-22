package io.github.sefiraat.slimetinker.utils;

import io.github.mooy1.infinitylib.common.StackUtils;
import io.github.sefiraat.slimetinker.SlimeTinker;
import io.github.sefiraat.slimetinker.events.friend.TraitPartType;
import io.github.sefiraat.slimetinker.i18n.TinkerLang;
import io.github.thebusybiscuit.slimefun5.core.services.localization.Language;
import io.github.sefiraat.slimetinker.items.tinkermaterials.TinkerMaterialManager;
import io.github.sefiraat.slimetinker.items.tinkermaterials.recipes.MoltenResult;
import io.github.sefiraat.slimetinker.modifiers.Mod;
import io.github.sefiraat.slimetinker.modifiers.Modifications;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.sefiraat.slimetinker.compat.Pdc;
import io.github.thebusybiscuit.slimefun5.libraries.dough.items.CustomItemStack;
import net.md_5.bungee.api.ChatColor;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ItemUtils {

    private ItemUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void incrementRepair(@Nonnull ItemStack itemStack) {
        ItemMeta im = itemStack.getItemMeta();
        Damageable damageable = (Damageable) im;
        assert damageable != null;
        damageable.setDamage(Math.max((damageable.getDamage() - 1), 0));
        itemStack.setItemMeta(im);
    }

    public static void incrementRepair(@Nonnull ItemStack itemStack, int amount) {
        ItemMeta im = itemStack.getItemMeta();
        Damageable damageable = (Damageable) im;
        assert damageable != null;
        damageable.setDamage(Math.max((damageable.getDamage() - amount), 0));
        itemStack.setItemMeta(im);
    }

    @Nullable
    public static String getItemName(ItemStack itemStack) {
        SlimefunItem slimefunItem = SlimefunItem.getByItem(itemStack);
        if (slimefunItem == null) {
            if (!itemStack.hasItemMeta()) {
                return itemStack.getType().toString();
            }
        } else {
            return slimefunItem.getId();
        }
        return null;
    }

    @Nullable
    public static String getToolMaterial(@Nonnull ItemStack itemStack) {
        ItemMeta im = itemStack.getItemMeta();
        if (im == null) {
            return null;
        }
        if (!Pdc.hasString(im, Keys.TOOL_INFO_HEAD_MATERIAL.toString())) {
            return null;
        }
        return Pdc.getString(im, Keys.TOOL_INFO_HEAD_MATERIAL.toString());
    }

    @Nullable
    public static String getArmourMaterial(@Nonnull ItemStack itemStack) {
        ItemMeta im = itemStack.getItemMeta();
        if (im == null) {
            return null;
        }
        if (!Pdc.hasString(im, Keys.ARMOUR_INFO_PLATE_MATERIAL.toString())) {
            return null;
        }
        return Pdc.getString(im, Keys.ARMOUR_INFO_PLATE_MATERIAL.toString());
    }

    @Nullable
    public static String getPartMaterial(@Nonnull ItemStack itemStack) {
        ItemMeta im = itemStack.getItemMeta();
        if (im == null) {
            return null;
        }
        if (!Pdc.hasString(im, Keys.PART_MATERIAL.toString())) {
            return null;
        }
        return Pdc.getString(im, Keys.PART_MATERIAL.toString());
    }

    /**
     * Gets the part's class (Head, Rod, Binder or Plate, Link, Gambeson)
     *
     * @param itemStack The {@link ItemStack} part to check
     * @return Null if not found or the string class.
     */
    @Nullable
    public static String getPartClass(@Nonnull ItemStack itemStack) {
        ItemMeta im = itemStack.getItemMeta();
        return im == null ? null : Pdc.getString(im, Keys.PART_CLASS.toString());
    }

    public static boolean partIsTool(@Nonnull String partClass) {
        return partClass.equals(Ids.HEAD)
            || partClass.equals(Ids.BINDING)
            || partClass.equals(Ids.ROD);
    }

    public static boolean partIsArmour(@Nonnull String partClass) {
        return partClass.equals(Ids.PLATE)
            || partClass.equals(Ids.GAMBESON)
            || partClass.equals(Ids.LINKS);
    }

    /**
     * Gets the part's type (Pick, Shovel // Helm, Chest) etc.)
     *
     * @param itemStack The {@link ItemStack} part to check
     * @return Null if not found or the string class.
     */
    @Nullable
    public static String getPartType(@Nonnull ItemStack itemStack) {
        ItemMeta im = itemStack.getItemMeta();
        return im == null ? null : Pdc.getString(im, Keys.PART_TYPE.toString());
    }

    /** The server's default language id, used when no holding player is in context. */
    @Nonnull
    public static String serverDefaultLanguage() {
        Language language = Slimefun.getLocalization().getDefaultLanguage();
        return language != null ? language.getId() : "en";
    }

    /** A translated lore label, falling back to the given English default (so English is unchanged). */
    @Nonnull
    private static String loreText(@Nonnull String key, @Nonnull String englishDefault, @Nullable String language) {
        String t = TinkerLang.lookup("lore", key, language);
        return t != null ? t : englishDefault;
    }

    public static void rebuildTinkerLore(@Nonnull ItemStack itemStack) {
        rebuildTinkerLore(itemStack, serverDefaultLanguage());
    }

    public static void rebuildTinkerLore(@Nonnull ItemStack itemStack, @Nullable String language) {
        if (isTool(itemStack)) {
            rebuildToolLore(itemStack, language);
        } else if (isArmour(itemStack)) {
            rebuildArmourLore(itemStack, language);
        }
    }

    public static void rebuildArmourLore(@Nonnull ItemStack itemStack) {
        rebuildArmourLore(itemStack, serverDefaultLanguage());
    }

    private static void rebuildToolLore(@Nonnull ItemStack itemStack, @Nullable String language) {
        ItemMeta im = itemStack.getItemMeta();
        assert im != null;
        im.setLore(buildToolLore(im, itemStack, language));
        itemStack.setItemMeta(im);
    }

    private static void rebuildArmourLore(@Nonnull ItemStack itemStack, @Nullable String language) {
        ItemMeta im = itemStack.getItemMeta();
        assert im != null;
        im.setLore(buildArmourLore(im, itemStack, language));
        itemStack.setItemMeta(im);
    }

    /**
     * Builds a part's lore in the given language from its persistent data.
     *
     * @implNote Parts used to carry this baked in from {@code PartTemplate#getStack}, so it could never be
     *           per-viewer and a part taken from the guide (which has no baked lore) showed none at all.
     */
    @Nonnull
    public static List<String> buildPartLore(@Nonnull ItemMeta im, @Nullable String language) {
        List<String> lore = new ArrayList<>();

        lore.add("");
        lore.add(ThemeUtils.PASSIVE + loreText("part_desc_1", "A tool part. Useless on it's own but can", language));
        lore.add(ThemeUtils.PASSIVE + loreText("part_desc_2", "be made into something greater at the", language));
        lore.add(ThemeUtils.PASSIVE + loreText("part_desc_3", "Tinker's table.", language));
        lore.add("");
        lore.add(ThemeUtils.CLICK_INFO + loreText("part_material", "Material : ", language)
            + formatMaterialName(Pdc.getString(im, Keys.PART_MATERIAL.toString()), language));

        return lore;
    }

    /** Builds the full tool lore in the given language (labels, traits and modifier names translated). */
    @Nonnull
    public static List<String> buildToolLore(@Nonnull ItemMeta im, @Nonnull ItemStack itemStack, @Nullable String language) {
        List<String> lore = new ArrayList<>();

        String matHead = getToolHeadMaterial(im);
        String matBind = getToolBindingMaterial(im);
        String matRod = getToolRodMaterial(im);

        lore.add(ThemeUtils.getLine());
        lore.add(ThemeUtils.CLICK_INFO + loreText("head_abbrev", "H: ", language) + formatMaterialName(matHead, language));
        lore.add(ThemeUtils.CLICK_INFO + loreText("binder_abbrev", "B: ", language) + formatMaterialName(matBind, language));
        lore.add(ThemeUtils.CLICK_INFO + loreText("rod_abbrev", "R: ", language) + formatMaterialName(matRod, language));
        lore.add(ThemeUtils.getLine());

        lore.add(formatPropertyName(matHead, TinkerMaterialManager.getTraitName(matHead, TraitPartType.HEAD), language));
        lore.add(formatPropertyName(matBind, TinkerMaterialManager.getTraitName(matBind, TraitPartType.BINDER), language));
        lore.add(formatPropertyName(matRod, TinkerMaterialManager.getTraitName(matRod, TraitPartType.ROD), language));
        lore.add(ThemeUtils.getLine());

        lore.add(getLoreExp(im, language));
        lore.add(getLoreModSlots(im, language));
        lore.add(ThemeUtils.getLine());

        Map<String, Integer> mapAmounts = Modifications.getModificationMapTool(itemStack);
        Map<String, Integer> mapLevels = Modifications.getAllModLevels(itemStack);

        for (Map.Entry<String, Integer> entry : mapLevels.entrySet()) {
            int level = entry.getValue();
            Mod mod = Modifications.getModificationDefinitionsTool().get(entry.getKey());
            String modName = modifierName(entry.getKey(), language);
            if (mod.getRequirementMap().containsKey(level + 1)) {
                String amountRequired = String.valueOf(mod.getRequirementMap().get(level + 1));
                lore.add(ThemeUtils.CLICK_INFO + modName + loreText("mod_level", " Level ", language) + entry.getValue() + ThemeUtils.PASSIVE + " - (" + mapAmounts.get(entry.getKey()) + "/" + amountRequired + ")");
            } else {
                lore.add(ThemeUtils.CLICK_INFO + modName + loreText("mod_level", " Level ", language) + entry.getValue() + ThemeUtils.PASSIVE + loreText("mod_max", " - (MAX)", language));
            }
        }
        if (!mapLevels.isEmpty()) {
            lore.add(ThemeUtils.getLine());
        }

        return lore;
    }

    /** Builds the full armour lore in the given language. */
    @Nonnull
    public static List<String> buildArmourLore(@Nonnull ItemMeta im, @Nonnull ItemStack itemStack, @Nullable String language) {
        List<String> lore = new ArrayList<>();

        String matPlate = getArmourPlateMaterial(im);
        String matGambeson = getArmourGambesonMaterial(im);
        String matLinks = getArmourLinksMaterial(im);

        lore.add(ThemeUtils.getLine());
        lore.add(ThemeUtils.CLICK_INFO + loreText("plate_abbrev", "P: ", language) + formatMaterialName(matPlate, language));
        lore.add(ThemeUtils.CLICK_INFO + loreText("gambeson_abbrev", "G: ", language) + formatMaterialName(matGambeson, language));
        lore.add(ThemeUtils.CLICK_INFO + loreText("links_abbrev", "L: ", language) + formatMaterialName(matLinks, language));
        lore.add(ThemeUtils.getLine());

        lore.add(formatPropertyName(matPlate, TinkerMaterialManager.getTraitName(matPlate, TraitPartType.PLATES), language));
        lore.add(formatPropertyName(matGambeson, TinkerMaterialManager.getTraitName(matGambeson, TraitPartType.GAMBESON), language));
        lore.add(formatPropertyName(matLinks, TinkerMaterialManager.getTraitName(matLinks, TraitPartType.LINKS), language));
        lore.add(ThemeUtils.getLine());

        lore.add(getLoreExp(im, language));
        lore.add(getLoreModSlots(im, language));
        lore.add(ThemeUtils.getLine());

        Map<String, Integer> mapAmounts = Modifications.getModificationMapArmour(itemStack);
        Map<String, Integer> mapLevels = Modifications.getAllModLevels(itemStack);

        for (Map.Entry<String, Integer> entry : mapLevels.entrySet()) {
            int level = entry.getValue();
            Mod mod = Modifications.getModificationDefinitionsArmour().get(entry.getKey());
            String modName = modifierName(entry.getKey(), language);
            if (mod.getRequirementMap().containsKey(level + 1)) {
                String amountRequired = String.valueOf(mod.getRequirementMap().get(level + 1));
                lore.add(ThemeUtils.CLICK_INFO + modName + loreText("mod_level", " Level ", language) + entry.getValue() + ThemeUtils.PASSIVE + " - (" + mapAmounts.get(entry.getKey()) + "/" + amountRequired + ")");
            } else {
                lore.add(ThemeUtils.CLICK_INFO + modName + loreText("mod_level", " Level ", language) + entry.getValue() + ThemeUtils.PASSIVE + loreText("mod_max", " - (MAX)", language));
            }
        }
        if (!mapLevels.isEmpty()) {
            lore.add(ThemeUtils.getLine());
        }

        return lore;
    }

    /** A modifier's display name in the given language, falling back to the title-cased id (English). */
    @Nonnull
    private static String modifierName(@Nonnull String id, @Nullable String language) {
        String t = TinkerLang.lookup("modifiers", id, language);
        return t != null ? t : ThemeUtils.toTitleCase(id);
    }

    public static void rebuildTinkerName(@Nonnull ItemStack itemStack) {
        if (isTool(itemStack)) {
            rebuildToolName(itemStack);
        } else if (isArmour(itemStack)) {
            rebuildArmourName(itemStack);
        }
    }

    private static void rebuildToolName(@Nonnull ItemStack itemStack) {
        ItemMeta im = itemStack.getItemMeta();
        assert im != null;
        String matHead = getToolHeadMaterial(im);
        String matBind = getToolBindingMaterial(im);
        String matRod = getToolRodMaterial(im);
        String toolType = getToolTypeName(im);

        setName(itemStack, matHead, matBind, matRod, toolType);
    }

    private static void rebuildArmourName(@Nonnull ItemStack itemStack) {
        ItemMeta im = itemStack.getItemMeta();
        assert im != null;
        String matPlate = getArmourPlateMaterial(im);
        String matGambeson = getArmourGambesonMaterial(im);
        String matLinks = getArmourLinksMaterial(im);
        String armourType = getArmourTypeName(im);

        setName(itemStack, matPlate, matGambeson, matLinks, armourType);
    }

    @ParametersAreNonnullByDefault
    private static void setName(ItemStack itemStack, String first, String second, String third, String type) {
        final ItemMeta im = itemStack.getItemMeta();
        final String name = MessageFormat.format(
            "{0}{1}-{2}{3}-{4}{5} {6}{7}",
            TinkerMaterialManager.getById(first).getColor(),
            ThemeUtils.toTitleCase(first),
            TinkerMaterialManager.getById(second).getColor(),
            ThemeUtils.toTitleCase(second),
            TinkerMaterialManager.getById(third).getColor(),
            ThemeUtils.toTitleCase(third),
            ChatColor.WHITE,
            ThemeUtils.toTitleCase(type)
        );

        im.setDisplayName(name);
        itemStack.setItemMeta(im);
    }

    public static boolean isTinkersBroken(@Nonnull ItemStack itemStack) {
        Damageable damageable = (Damageable) itemStack.getItemMeta();
        return damageable.getDamage() == itemStack.getType().getMaxDurability() - 1;
    }

    public static void damageTinkersItem(@Nonnull ItemStack itemStack, int amount) {
        ItemMeta im = itemStack.getItemMeta();
        Damageable damageable = (Damageable) im;
        if ((damageable.getDamage() + amount) >= itemStack.getType().getMaxDurability()) { // This will break the tool, lets stop that!
            damageable.setDamage(itemStack.getType().getMaxDurability() - 1);
        } else {
            damageable.setDamage(damageable.getDamage() + amount);
        }
        itemStack.setItemMeta(im);
    }

    public static void repairItem(@Nonnull ItemStack itemStack) {
        ItemMeta im = itemStack.getItemMeta();
        Damageable d = (Damageable) im;
        d.setDamage(0);
        itemStack.setItemMeta(im);
    }

    public static void repairItem(@Nonnull ItemStack itemStack, int amount) {
        ItemMeta im = itemStack.getItemMeta();
        Damageable d = (Damageable) im;
        d.setDamage(Math.max(d.getDamage() - amount, 0));
        itemStack.setItemMeta(im);
    }

    @Nullable
    public static String getToolHeadMaterial(@Nonnull ItemStack itemStack) {
        return getToolHeadMaterial(itemStack.getItemMeta());
    }

    @Nullable
    public static String getToolHeadMaterial(@Nonnull ItemMeta im) {
        return Pdc.getString(im, Keys.TOOL_INFO_HEAD_MATERIAL.toString());
    }

    @Nullable
    public static String getToolBindingMaterial(@Nonnull ItemStack itemStack) {
        return getToolBindingMaterial(itemStack.getItemMeta());
    }

    @Nullable
    public static String getToolBindingMaterial(@Nonnull ItemMeta im) {
        return Pdc.getString(im, Keys.TOOL_INFO_BINDER_MATERIAL.toString());
    }

    @Nullable
    public static String getToolRodMaterial(@Nonnull ItemStack itemStack) {
        return getToolRodMaterial(itemStack.getItemMeta());
    }

    @Nullable
    public static String getToolRodMaterial(@Nonnull ItemMeta im) {
        return Pdc.getString(im, Keys.TOOL_INFO_ROD_MATERIAL.toString());
    }

    @Nullable
    public static String getToolTypeName(@Nonnull ItemStack itemStack) {
        return getToolTypeName(itemStack.getItemMeta());
    }

    @Nullable
    public static String getToolTypeName(@Nonnull ItemMeta im) {
        return Pdc.getString(im, Keys.TOOL_INFO_TOOL_TYPE.toString());
    }

    @Nullable
    public static String getArmourPlateMaterial(@Nonnull ItemStack itemStack) {
        return getArmourPlateMaterial(itemStack.getItemMeta());
    }

    @Nullable
    public static String getArmourPlateMaterial(@Nonnull ItemMeta im) {
        return Pdc.getString(im, Keys.ARMOUR_INFO_PLATE_MATERIAL.toString());
    }

    @Nullable
    public static String getArmourGambesonMaterial(@Nonnull ItemStack itemStack) {
        return getArmourGambesonMaterial(itemStack.getItemMeta());
    }

    @Nullable
    public static String getArmourGambesonMaterial(@Nonnull ItemMeta im) {
        return Pdc.getString(im, Keys.ARMOUR_INFO_GAMBESON_MATERIAL.toString());
    }

    @Nullable
    public static String getArmourLinksMaterial(@Nonnull ItemStack itemStack) {
        return getArmourLinksMaterial(itemStack.getItemMeta());
    }

    @Nullable
    public static String getArmourLinksMaterial(@Nonnull ItemMeta im) {
        return Pdc.getString(im, Keys.ARMOUR_INFO_LINKS_MATERIAL.toString());
    }

    @Nullable
    public static String getArmourTypeName(@Nonnull ItemStack itemStack) {
        return getArmourTypeName(itemStack.getItemMeta());
    }

    @Nullable
    public static String getArmourTypeName(@Nonnull ItemMeta im) {
        return Pdc.getString(im, Keys.ARMOUR_INFO_ARMOUR_TYPE.toString());
    }

    @Nonnull
    public static String formatMaterialName(String s) {
        return formatMaterialName(s, serverDefaultLanguage());
    }

    public static String formatMaterialName(String s, @Nullable String language) {
        return TinkerMaterialManager.getById(s).getColor() + TinkerLang.translate("materials", s, language);
    }

    @Nonnull
    public static String formatPropertyName(String s, String p) {
        return formatPropertyName(s, p, serverDefaultLanguage());
    }

    /** Colours a trait name, translating it via the "traits" table (English name as key) with fallback to the name. */
    @Nonnull
    public static String formatPropertyName(String s, String p, @Nullable String language) {
        String translated = TinkerLang.lookup("traits", p, language);
        return TinkerMaterialManager.getColorById(s) + (translated != null ? translated : p);
    }

    public static boolean isMeltable(ItemStack itemStack) {
        return SlimeTinker.getInstance().getCmManager().meltingRecipes.containsKey(StackUtils.getIdOrType(itemStack));
    }

    public static MoltenResult getMoltenResult(ItemStack itemStack) {
        return SlimeTinker.getInstance().getCmManager().meltingRecipes.get(StackUtils.getIdOrType(itemStack));
    }

    public static boolean doesUnequipWhenBroken(ItemStack itemStack) {
        return !getArmourPlateMaterial(itemStack).equals(Ids.DURALUMIN);
    }

    /**
     * Checks if the given stack is a Tinker's Tool
     *
     * @param itemStack Stack to check
     * @return true if Tinker's Tool
     */
    public static boolean isTool(@Nullable ItemStack itemStack) {
        return itemStack != null &&
            itemStack.getType() != MaterialCompat.safe(XMaterial.AIR) &&
            itemStack.hasItemMeta() &&
            Pdc.hasString(itemStack.getItemMeta(), Keys.TOOL_INFO_TOOL_TYPE.toString());
    }

    /**
     * Checks if the given stack is a Tinker's Armour Piece
     *
     * @param itemStack Stack to check
     * @return true if Tinker's Armour Piece
     */
    public static boolean isArmour(@Nullable ItemStack itemStack) {
        return itemStack != null &&
            itemStack.getType() != MaterialCompat.safe(XMaterial.AIR) &&
            itemStack.hasItemMeta() &&
            Pdc.hasString(itemStack.getItemMeta(), Keys.ARMOUR_INFO_ARMOUR_TYPE.toString());
    }

    public static boolean isTinkers(@Nullable ItemStack itemStack) {
        return isTool(itemStack) || isArmour(itemStack);
    }

    public static boolean doesNotWorkWhenBroken(ItemStack itemStack) {
        if (isTool(itemStack)) {
            return
                !getToolHeadMaterial(itemStack).equals(Ids.DURALUMIN)
                    && !getToolRodMaterial(itemStack).equals(Ids.TITANIUM);

        }
        return true;
    }

    public static boolean repairBenchEasyFix(ItemStack itemStack) {
        return repairBenchEasyFix1(itemStack) || repairBenchEasyFix2(itemStack);
    }

    public static boolean repairBenchEasyFix1(ItemStack itemStack) {
        if (isTool(itemStack)) {
            return getToolRodMaterial(itemStack).equals(Ids.DURALUMIN);
        } else if (isArmour(itemStack)) {
            return getArmourLinksMaterial(itemStack).equals(Ids.ALUMINUM);
        }
        return false;
    }

    public static boolean repairBenchEasyFix2(ItemStack itemStack) {
        if (isArmour(itemStack)) {
            return getArmourLinksMaterial(itemStack).equals(Ids.ALUMINUM_SINGULARITY);
        }
        return false;
    }

    public static boolean repairBenchCraftsman(ItemStack itemStack) {
        if (isArmour(itemStack)) {
            return getArmourLinksMaterial(itemStack).equals(Ids.SMITHIUM);
        } else {
            return false;
        }
    }

    public static boolean isEnchanting(ItemStack itemStack) {
        if (isTool(itemStack)) {
            return getToolHeadMaterial(itemStack).equals(Ids.SILVER)
                || getToolHeadMaterial(itemStack).equals(Ids.SILVER_SINGULARITY);
        } else if (isArmour(itemStack)) {
            return getArmourLinksMaterial(itemStack).equals(Ids.SILVER)
                || getArmourLinksMaterial(itemStack).equals(Ids.SILVER_SINGULARITY);
        } else {
            return false;
        }
    }

    public static boolean isEnchanting1(ItemStack itemStack) {
        if (isTool(itemStack)) {
            return getToolHeadMaterial(itemStack).equals(Ids.SILVER);
        } else if (isArmour(itemStack)) {
            return getArmourLinksMaterial(itemStack).equals(Ids.SILVER);
        } else {
            return false;
        }
    }

    public static boolean isEnchanting2(ItemStack itemStack) {
        if (isTool(itemStack)) {
            return getToolHeadMaterial(itemStack).equals(Ids.SILVER_SINGULARITY);
        } else if (isArmour(itemStack)) {
            return getArmourLinksMaterial(itemStack).equals(Ids.SILVER_SINGULARITY);
        } else {
            return false;
        }
    }

    public static boolean isConductive(ItemStack itemStack) {
        if (isTool(itemStack)) {
            return getToolRodMaterial(itemStack).equals(Ids.COPPER)
                || getToolRodMaterial(itemStack).equals(Ids.COPPER_SINGULARITY);
        } else {
            return false;
        }
    }

    public static boolean isConductive1(ItemStack itemStack) {
        if (isTool(itemStack)) {
            return getToolRodMaterial(itemStack).equals(Ids.COPPER);
        } else {
            return false;
        }
    }

    public static boolean isConductive2(ItemStack itemStack) {
        if (isTool(itemStack)) {
            return getToolRodMaterial(itemStack).equals(Ids.COPPER_SINGULARITY);
        } else {
            return false;
        }
    }

    public static boolean cannotDrop(ItemStack itemStack) {
        if (isTool(itemStack)) {
            return
                getToolRodMaterial(itemStack).equals(Ids.SOLDER)
                    || getToolRodMaterial(itemStack).equals(Ids.UNPATENTABLIUM);
        } else if (isArmour(itemStack)) {
            return
                getArmourLinksMaterial(itemStack).equals(Ids.SOLDER);
        } else {
            return false;
        }

    }

    public static boolean isReinforced(ItemStack itemStack) {
        if (isTool(itemStack)) {
            return getToolRodMaterial(itemStack).equals(Ids.REINFORCED_ALLOY);
        } else if (isArmour(itemStack)) {
            return getArmourPlateMaterial(itemStack).equals(Ids.REINFORCED_ALLOY);
        } else {
            return false;
        }
    }

    public static boolean isExperienced(ItemStack itemStack) {
        return getArmourPlateMaterial(itemStack).equals(Ids.REINFORCED_ALLOY);
    }

    public static int getTinkerExp(@Nonnull ItemMeta im) {
        return Pdc.getInt(im, Keys.ST_EXP_CURRENT.toString(), 0);
    }

    public static int getTinkerExp(ItemStack itemStack) {
        if (itemStack == null) return 0;
        return getTinkerExp(itemStack.getItemMeta());
    }

    public static int getTinkerRequiredExp(@Nonnull ItemMeta im) {
        return (int) Pdc.getDouble(im, Keys.ST_EXP_REQUIRED.toString(), 0);
    }

    public static int getTinkerRequiredExp(ItemStack itemStack) {
        if (itemStack == null) return 0;
        return getTinkerRequiredExp(itemStack.getItemMeta());
    }

    public static int getTinkerLevel(@Nonnull ItemMeta im) {
        return Pdc.getInt(im, Keys.ST_LEVEL.toString(), 0);
    }

    public static int getTinkerLevel(ItemStack itemStack) {
        if (itemStack == null) return 0;
        return getTinkerLevel(itemStack.getItemMeta());
    }

    public static int getTinkerModifierSlots(@Nonnull ItemMeta im) {
        return Pdc.getInt(im, Keys.ST_MOD_SLOTS.toString(), 0);
    }

    public static int getTinkerModifierSlots(ItemStack itemStack) {
        if (itemStack == null) return 0;
        return getTinkerModifierSlots(itemStack.getItemMeta());
    }

    public static void setTinkerModifierSlots(@Nonnull ItemMeta im, int amount) {
        Pdc.setInt(im, Keys.ST_MOD_SLOTS.toString(), amount);
    }

    @Nonnull
    public static String getLoreExp(ItemMeta im) {
        return getLoreExp(im, serverDefaultLanguage());
    }

    @Nonnull
    public static String getLoreExp(ItemMeta im, @Nullable String language) {
        return ThemeUtils.ITEM_TOOL + loreText("level", "Level: ", language) +
            ChatColor.WHITE + getTinkerLevel(im) +
            ThemeUtils.PASSIVE + " (" + getTinkerExp(im) + " / " + getTinkerRequiredExp(im) + ")";
    }

    @Nonnull
    public static String getLoreModSlots(ItemMeta im) {
        return getLoreModSlots(im, serverDefaultLanguage());
    }

    @Nonnull
    public static String getLoreModSlots(ItemMeta im, @Nullable String language) {
        return ThemeUtils.ITEM_TOOL + loreText("modifier_slots", "Modifier Slots: ", language) +
            ChatColor.WHITE + getTinkerModifierSlots(im);
    }

    public static boolean rejectCraftingRecipe(@Nonnull SlimefunItemStack i) {
        return rejectCraftingRecipe(i.getItemId());
    }

    public static boolean rejectCraftingRecipe(@Nonnull SlimefunItem i) {
        return rejectCraftingRecipe(i.getId());
    }

    public static boolean rejectCraftingRecipe(String s) {
        return isBackpack(s);
    }

    public static boolean isBackpack(@Nonnull String s) {
        return s.matches("(.*)BACKPACK(.*)");
    }

    public static void incrementRandomEnchant(ItemStack i, ItemMeta im) {
        Enchantment randEnchant = Enchantment.values()[(GeneralUtils.roll(Enchantment.values().length, false))];
        if (im.hasEnchant(randEnchant)) {
            im.addEnchant(randEnchant, i.getEnchantmentLevel(randEnchant) + 1, true);
        } else {
            im.addEnchant(randEnchant, 1, true);
        }
    }

    public static boolean onCooldown(@Nonnull ItemStack i, String name) {
        ItemMeta im = i.getItemMeta();
        NamespacedKey key = new NamespacedKey(SlimeTinker.getInstance(), "cooldown_" + name);
        long time = System.currentTimeMillis();
        long cd = Pdc.getLong(im, key.toString(), 0);
        return cd > time;
    }

    public static void setCooldown(@Nonnull ItemStack i, String name, long duration) {
        ItemMeta im = i.getItemMeta();
        NamespacedKey key = new NamespacedKey(SlimeTinker.getInstance(), "cooldown_" + name);
        long time = System.currentTimeMillis();
        long cd = time + duration;
        Pdc.setLong(im, key.toString(), cd);
        i.setItemMeta(im);
    }

    public static boolean isToolExplosive(@Nonnull ItemStack itemStack) {
        return isToolExplosive(itemStack.getItemMeta());
    }

    public static boolean isToolExplosive(@Nonnull ItemMeta im) {
        String sfIDKey = new NamespacedKey(Slimefun.instance(), "slimefun_item").toString();
        String sID = Pdc.getString(im, sfIDKey);
        return sID.contains("_EXP");
    }

    public static boolean isToolExplosive(@Nonnull String headMaterial, String rodMaterial) {
        return headMaterial.equals(Ids.REINFORCED_ALLOY)
            || rodMaterial.equals(Ids.HARDENED_METAL)
            || headMaterial.equals(Ids.INFINITY_SINGULARITY)
            || headMaterial.equals(Ids.OSMIUM);
    }

    @Nullable
    public static ItemStack getItemByID(@Nonnull String id) {
        return getItemByID(id, 1);
    }

    @Nullable
    public static ItemStack getItemByID(@Nonnull String id, int amount) {
        SlimefunItem sfItem = SlimefunItem.getById(id);
        if (sfItem != null) {
            return CustomItemStack.create(sfItem.getItem(), amount);
        } else {
            return null;
        }
    }

    @Nonnull
    public static ItemStack[] getMiddleOnlyRecipe(@Nonnull ItemStack item) {
        return new ItemStack[]{
            null, null, null,
            null, item, null,
            null, null, null
        };
    }
}


