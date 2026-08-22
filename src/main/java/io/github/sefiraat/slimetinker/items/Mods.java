package io.github.sefiraat.slimetinker.items;

import io.github.sefiraat.slimetinker.SlimeTinker;
import io.github.sefiraat.slimetinker.itemgroups.ItemGroups;
import io.github.sefiraat.slimetinker.items.workstations.modificationstation.DummyModificationStation;
import io.github.sefiraat.slimetinker.utils.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import org.bukkit.inventory.ItemStack;

public final class Mods {

    private Mods() {
        throw new UnsupportedOperationException("Utility Class");
    }

    public static final SlimefunItemStack MOD_REDSTONE_ENTRY = new BaseItem("MOD_REDSTONE_ENTRY", MaterialCompat.safe(XMaterial.REDSTONE));

    public static final SlimefunItemStack MOD_LAPIS_ENTRY = new BaseItem("MOD_LAPIS_ENTRY", MaterialCompat.safe(XMaterial.LAPIS_LAZULI));

    public static final SlimefunItemStack MOD_QUARTZ_ENTRY = new BaseItem("MOD_QUARTZ_ENTRY", MaterialCompat.safe(XMaterial.QUARTZ));

    public static final SlimefunItemStack MOD_DIAMOND_ENTRY = new BaseItem("MOD_DIAMOND_ENTRY", MaterialCompat.safe(XMaterial.DIAMOND));

    public static final SlimefunItemStack MOD_EMERALD_ENTRY = new BaseItem("MOD_EMERALD_ENTRY", MaterialCompat.safe(XMaterial.EMERALD));


    public static final SlimefunItemStack MOD_PLATE_ENTRY = new BaseItem("MOD_PLATE_ENTRY", MaterialCompat.safe(XMaterial.OBSIDIAN));


    public static void set(SlimeTinker p) {
        // Dummies for the recipe book
        new UnplaceableBlock(ItemGroups.MODIFICATIONS, MOD_REDSTONE_ENTRY, DummyModificationStation.TYPE, new ItemStack[9]).register(p);
        new UnplaceableBlock(ItemGroups.MODIFICATIONS, MOD_LAPIS_ENTRY, DummyModificationStation.TYPE, new ItemStack[9]).register(p);
        new UnplaceableBlock(ItemGroups.MODIFICATIONS, MOD_QUARTZ_ENTRY, DummyModificationStation.TYPE, new ItemStack[9]).register(p);
        new UnplaceableBlock(ItemGroups.MODIFICATIONS, MOD_DIAMOND_ENTRY, DummyModificationStation.TYPE, new ItemStack[9]).register(p);
        new UnplaceableBlock(ItemGroups.MODIFICATIONS, MOD_EMERALD_ENTRY, DummyModificationStation.TYPE, new ItemStack[9]).register(p);
        new UnplaceableBlock(ItemGroups.MODIFICATIONS, MOD_PLATE_ENTRY, DummyModificationStation.TYPE, new ItemStack[9]).register(p);
    }
}

