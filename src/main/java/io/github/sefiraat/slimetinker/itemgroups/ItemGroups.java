package io.github.sefiraat.slimetinker.itemgroups;

import io.github.sefiraat.slimetinker.SlimeTinker;
import io.github.sefiraat.slimetinker.utils.Keys;
import io.github.sefiraat.slimetinker.utils.SkullTextures;
import io.github.sefiraat.slimetinker.utils.ThemeUtils;
import io.github.thebusybiscuit.slimefun5.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun5.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun5.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun5.utils.compatibility.VersionedPlayerHead;
import io.github.sefiraat.slimetinker.utils.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;

public final class ItemGroups {

    private ItemGroups() {
        throw new UnsupportedOperationException("Utility Class");
    }

    public static final DummyItemGroup WORKSTATIONS = new DummyItemGroup(Keys.ITEM_GROUP_WORKSTATIONS, CustomItemStack.create(VersionedPlayerHead.getItemStack(SkullTextures.CAT_MAIN), ThemeUtils.MAIN + "Workstations"));
    public static final DummyItemGroup MATERIALS = new DummyItemGroup(Keys.ITEM_GROUP_MATERIALS, CustomItemStack.create(VersionedPlayerHead.getItemStack(SkullTextures.CAT_MATERIALS), ThemeUtils.MAIN + "Materials"));
    public static final DummyItemGroup MOLTEN_METALS = new DummyItemGroup(Keys.ITEM_GROUP_MOLTEN, CustomItemStack.create(VersionedPlayerHead.getItemStack(SkullTextures.CAT_MOLTEN), ThemeUtils.MAIN + "Molten Metals"));
    public static final DummyItemGroup ALLOYS = new DummyItemGroup(Keys.ITEM_GROUP_ALLOYS, CustomItemStack.create(VersionedPlayerHead.getItemStack(SkullTextures.CAT_ALLOYS), ThemeUtils.MAIN + "Alloy Metals"));
    public static final DummyItemGroup CASTS = new DummyItemGroup(Keys.ITEM_GROUP_CASTS, CustomItemStack.create(VersionedPlayerHead.getItemStack(SkullTextures.CAT_CASTS), ThemeUtils.MAIN + "Casts"));
    public static final DummyItemGroup PARTS = new DummyItemGroup(Keys.ITEM_GROUP_PARTS, CustomItemStack.create(VersionedPlayerHead.getItemStack(SkullTextures.CAT_PARTS), ThemeUtils.MAIN + "Part Building Guide"));
    public static final DummyItemGroup TOOLS = new DummyItemGroup(Keys.ITEM_GROUP_TOOLS, CustomItemStack.create(MaterialCompat.safe(XMaterial.DIAMOND_SWORD), ThemeUtils.MAIN + "Tool Building Guide"));
    public static final DummyItemGroup ARMOUR = new DummyItemGroup(Keys.ITEM_GROUP_ARMOUR, CustomItemStack.create(MaterialCompat.safe(XMaterial.DIAMOND_CHESTPLATE), ThemeUtils.MAIN + "Armour Building Guide"));
    public static final DummyItemGroup TRAITS = new DummyItemGroup(Keys.ITEM_GROUP_PROPERTIES, CustomItemStack.create(VersionedPlayerHead.getItemStack(SkullTextures.CAT_TRAITS), ThemeUtils.MAIN + "Metal Traits"));
    public static final DummyItemGroup MODIFICATIONS = new DummyItemGroup(Keys.ITEM_GROUP_MODIFICATIONS, CustomItemStack.create(MaterialCompat.safe(XMaterial.REDSTONE), ThemeUtils.MAIN + "Mods"));
    public static final DummyItemGroup PART_DICT = new DummyItemGroup(Keys.ITEM_GROUP_PART_DICT, CustomItemStack.create(VersionedPlayerHead.getItemStack(SkullTextures.PART_PICKAXE_HEAD), ThemeUtils.MAIN + "Part Dictionary"));
    public static final DummyItemGroup DUMMY = new DummyItemGroup(Keys.ITEM_GROUP_DUMMY, CustomItemStack.create(MaterialCompat.safe(XMaterial.BARRIER), ThemeUtils.MAIN + "SlimeTinker Dummy"), true);

    public static void set(SlimeTinker p) {
        WORKSTATIONS.setTheme("machines");
        MATERIALS.setTheme("resources");
        MOLTEN_METALS.setTheme("resources");
        ALLOYS.setTheme("resources");
        CASTS.setTheme("resources");
        PARTS.setTheme("resources");
        TOOLS.setTheme("tools");
        ARMOUR.setTheme("armor");
        TRAITS.setTheme("tools");
        MODIFICATIONS.setTheme("tools");
        PART_DICT.setTheme("resources");

        WORKSTATIONS.register(p);
        MATERIALS.register(p);
        MOLTEN_METALS.register(p);
        ALLOYS.register(p);
        CASTS.register(p);
        PARTS.register(p);
        TOOLS.register(p);
        ARMOUR.register(p);
        TRAITS.register(p);
        MODIFICATIONS.register(p);
        PART_DICT.register(p);
    }

    // Assign each group's registered items to a shared guide category. Head-textured and
    // dynamically-built items are missed by the core heuristic, so we classify by group.
    public static void categorise() {
        setGuideType(WORKSTATIONS, "machines");
        setGuideType(MATERIALS, "resources");
        setGuideType(MOLTEN_METALS, "resources");
        setGuideType(ALLOYS, "resources");
        setGuideType(CASTS, "resources");
        setGuideType(PARTS, "resources");
        setGuideType(TOOLS, "tools");
        setGuideType(ARMOUR, "armor");
        setGuideType(TRAITS, "magic");
        setGuideType(MODIFICATIONS, "magic");
        setGuideType(PART_DICT, "resources");
    }

    private static void setGuideType(ItemGroup group, String categoryId) {
        for (SlimefunItem item : group.getItems()) {
            item.setGuideType(categoryId);
        }
    }

}

