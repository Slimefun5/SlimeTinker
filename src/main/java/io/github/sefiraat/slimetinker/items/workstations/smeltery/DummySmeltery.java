package io.github.sefiraat.slimetinker.items.workstations.smeltery;

import io.github.sefiraat.slimetinker.utils.Keys;
import io.github.sefiraat.slimetinker.utils.ThemeUtils;
import io.github.sefiraat.slimetinker.utils.enums.ThemeItemType;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.sefiraat.slimetinker.utils.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;

public final class DummySmeltery {

    public static final SlimefunItemStack STACK = ThemeUtils.themedItemStack(
        "DUMMY_TINKERS_SMELTERY",
        MaterialCompat.safe(XMaterial.CHISELED_POLISHED_BLACKSTONE),
        ThemeItemType.MACHINE,
        "Tinker's Smeltery",
        "This item is cast out from the",
        "tinkers smeltery using the",
        "relevant cast and metal/liquid."
    );
    public static final RecipeType TYPE = new RecipeType(Keys.sfKey(Keys.WS_DUMMY_SMELTERY), STACK);

    private DummySmeltery() {
        throw new IllegalStateException("Utility class");
    }
}

