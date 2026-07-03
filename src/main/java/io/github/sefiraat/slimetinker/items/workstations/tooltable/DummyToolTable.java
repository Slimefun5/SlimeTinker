package io.github.sefiraat.slimetinker.items.workstations.tooltable;

import io.github.sefiraat.slimetinker.items.BaseItem;

import io.github.sefiraat.slimetinker.utils.Keys;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.sefiraat.slimetinker.utils.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;

public final class DummyToolTable {

    public static final SlimefunItemStack STACK = new BaseItem("DUMMY_TINKERS_TABLE", MaterialCompat.safe(XMaterial.SMITHING_TABLE));
    public static final RecipeType TYPE = new RecipeType(Keys.sfKey(Keys.WS_DUMMY_TOOL_TABLE), STACK);

    private DummyToolTable() {
        throw new IllegalStateException("Utility class");
    }
}

