package io.github.sefiraat.slimetinker.items.workstations.tooltable;

import io.github.sefiraat.slimetinker.utils.Keys;
import io.github.sefiraat.slimetinker.utils.ThemeUtils;
import io.github.sefiraat.slimetinker.utils.enums.ThemeItemType;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.sefiraat.slimetinker.utils.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;

public final class DummyToolTable {

    public static final SlimefunItemStack STACK = ThemeUtils.themedItemStack(
        "DUMMY_TINKERS_TABLE",
        MaterialCompat.safe(XMaterial.SMITHING_TABLE),
        ThemeItemType.MACHINE,
        "Tinker's Tool Table",
        "This item is made on a Tinker's Tool Table."
    );
    public static final RecipeType TYPE = new RecipeType(Keys.sfKey(Keys.WS_DUMMY_TOOL_TABLE), STACK);

    private DummyToolTable() {
        throw new IllegalStateException("Utility class");
    }
}

