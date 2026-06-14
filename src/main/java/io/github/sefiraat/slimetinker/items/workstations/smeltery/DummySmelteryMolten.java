package io.github.sefiraat.slimetinker.items.workstations.smeltery;

import io.github.sefiraat.slimetinker.utils.Keys;
import io.github.sefiraat.slimetinker.utils.ThemeUtils;
import io.github.sefiraat.slimetinker.utils.enums.ThemeItemType;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.sefiraat.slimetinker.utils.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;

public final class DummySmelteryMolten {

    public static final SlimefunItemStack STACK = ThemeUtils.themedItemStack(
        "DUMMY_TINKERS_SMELTERY_MOLTEN",
        MaterialCompat.safe(XMaterial.CHISELED_POLISHED_BLACKSTONE),
        ThemeItemType.MACHINE,
        "Tinker's Smeltery",
        "Molten metals are made by putting",
        "items made from the metal into the",
        "Smeltery. Items melt when provided",
        "enough lava as a heat source."
    );
    public static final RecipeType TYPE = new RecipeType(Keys.sfKey(Keys.WS_DUMMY_SMELTERY_M), STACK);

    private DummySmelteryMolten() {
        throw new IllegalStateException("Utility class");
    }
}

