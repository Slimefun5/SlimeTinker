package io.github.sefiraat.slimetinker.items.workstations.smeltery;

import io.github.sefiraat.slimetinker.utils.Keys;
import io.github.sefiraat.slimetinker.utils.ThemeUtils;
import io.github.sefiraat.slimetinker.utils.enums.ThemeItemType;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.sefiraat.slimetinker.utils.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;

public final class DummySmelteryAlloy {

    public static final SlimefunItemStack STACK = ThemeUtils.themedItemStack(
        "DUMMY_TINKERS_SMELTERY_ALLOY",
        MaterialCompat.safe(XMaterial.CHISELED_POLISHED_BLACKSTONE),
        ThemeItemType.MACHINE,
        "Tinker's Smeltery",
        "This alloy is made in the Tinker's",
        "Smeltery by inputting correct metal",
        "types and clicking Alloy."
    );
    public static final RecipeType TYPE = new RecipeType(Keys.sfKey(Keys.WS_DUMMY_SMELTERY_A), STACK);

    private DummySmelteryAlloy() {
        throw new IllegalStateException("Utility class");
    }
}

