package io.github.sefiraat.slimetinker.itemgroups;

import io.github.sefiraat.slimetinker.SlimeTinker;
import io.github.sefiraat.slimetinker.utils.Keys;
import io.github.sefiraat.slimetinker.utils.SkullTextures;
import io.github.sefiraat.slimetinker.utils.ThemeUtils;
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

    public static final MainFlexGroup MAIN = new MainFlexGroup(Keys.ITEM_GROUP_MAIN, new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", VersionedPlayerHead.getItemStack(SkullTextures.CAT_MAIN), ThemeUtils.MAIN + "SlimeTinker"
    ).item());
    public static final DummyItemGroup WORKSTATIONS = new DummyItemGroup(Keys.ITEM_GROUP_WORKSTATIONS, new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", VersionedPlayerHead.getItemStack(SkullTextures.CAT_MAIN), ThemeUtils.MAIN + "Workstations"
    ).item());
    public static final DummyItemGroup MATERIALS = new DummyItemGroup(Keys.ITEM_GROUP_MATERIALS, new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", VersionedPlayerHead.getItemStack(SkullTextures.CAT_MATERIALS), ThemeUtils.MAIN + "Materials"
    ).item());
    public static final DummyItemGroup MOLTEN_METALS = new DummyItemGroup(Keys.ITEM_GROUP_MOLTEN, new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", VersionedPlayerHead.getItemStack(SkullTextures.CAT_MOLTEN), ThemeUtils.MAIN + "Molten Metals"
    ).item());
    public static final DummyItemGroup ALLOYS = new DummyItemGroup(Keys.ITEM_GROUP_ALLOYS, new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", VersionedPlayerHead.getItemStack(SkullTextures.CAT_ALLOYS), ThemeUtils.MAIN + "Alloy Metals"
    ).item());
    public static final DummyItemGroup CASTS = new DummyItemGroup(Keys.ITEM_GROUP_CASTS, new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", VersionedPlayerHead.getItemStack(SkullTextures.CAT_CASTS), ThemeUtils.MAIN + "Casts"
    ).item());
    public static final DummyItemGroup PARTS = new DummyItemGroup(Keys.ITEM_GROUP_PARTS, new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", VersionedPlayerHead.getItemStack(SkullTextures.CAT_PARTS), ThemeUtils.MAIN + "Part Building Guide"
    ).item());
    public static final DummyItemGroup TOOLS = new DummyItemGroup(Keys.ITEM_GROUP_TOOLS, new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.DIAMOND_SWORD), ThemeUtils.MAIN + "Tool Building Guide"
    ).item());
    public static final DummyItemGroup ARMOUR = new DummyItemGroup(Keys.ITEM_GROUP_ARMOUR, new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.DIAMOND_CHESTPLATE), ThemeUtils.MAIN + "Armour Building Guide"
    ).item());
    public static final DummyItemGroup TRAITS = new DummyItemGroup(Keys.ITEM_GROUP_PROPERTIES, new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", VersionedPlayerHead.getItemStack(SkullTextures.CAT_TRAITS), ThemeUtils.MAIN + "Metal Traits"
    ).item());
    public static final DummyItemGroup MODIFICATIONS = new DummyItemGroup(Keys.ITEM_GROUP_MODIFICATIONS, new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.REDSTONE), ThemeUtils.MAIN + "Mods"
    ).item());
    public static final DummyItemGroup PART_DICT = new DummyItemGroup(Keys.ITEM_GROUP_PART_DICT, new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", VersionedPlayerHead.getItemStack(SkullTextures.PART_PICKAXE_HEAD), ThemeUtils.MAIN + "Part Dictionary"
    ).item());
    public static final DummyItemGroup DUMMY = new DummyItemGroup(Keys.ITEM_GROUP_DUMMY, new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.BARRIER), ThemeUtils.MAIN + "SlimeTinker Dummy"
    ).item());

    public static void set(SlimeTinker p) {
        MAIN.setTheme("tools");
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

        MAIN.register(p);
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

}

