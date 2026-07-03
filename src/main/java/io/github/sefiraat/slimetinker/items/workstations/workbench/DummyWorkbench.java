package io.github.sefiraat.slimetinker.items.workstations.workbench;

import io.github.sefiraat.slimetinker.items.BaseItem;

import io.github.sefiraat.slimetinker.utils.Keys;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.sefiraat.slimetinker.utils.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;

public final class DummyWorkbench {

    public static final SlimefunItemStack STACK = new BaseItem("DUMMY_TINKERS_SMELTERY", MaterialCompat.safe(XMaterial.FLETCHING_TABLE));
    public static final RecipeType TYPE = new RecipeType(Keys.sfKey(Keys.WS_DUMMY_WORKBENCH), STACK);

    private DummyWorkbench() {
        throw new IllegalStateException("Utility class");
    }
}

