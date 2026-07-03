package io.github.sefiraat.slimetinker.items.workstations.armourtable;

import io.github.sefiraat.slimetinker.items.BaseItem;

import io.github.sefiraat.slimetinker.utils.Keys;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.sefiraat.slimetinker.utils.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;

public final class DummyArmourTable {

    public static final SlimefunItemStack STACK = new BaseItem("DUMMY_TINKERS_ARMOUR_TABLE", MaterialCompat.safe(XMaterial.CHISELED_NETHER_BRICKS));
    public static final RecipeType TYPE = new RecipeType(Keys.sfKey(Keys.WS_DUMMY_ARMOUR_TABLE), STACK);

    private DummyArmourTable() {
        throw new IllegalStateException("Utility class");
    }

}

