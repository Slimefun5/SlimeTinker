package io.github.sefiraat.slimetinker.items.workstations.modificationstation;

import io.github.sefiraat.slimetinker.utils.Keys;
import io.github.sefiraat.slimetinker.utils.ThemeUtils;
import io.github.sefiraat.slimetinker.utils.enums.ThemeItemType;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.sefiraat.slimetinker.utils.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;

public final class DummyModificationStation {

    public static final SlimefunItemStack STACK = ThemeUtils.themedItemStack(
        "DUMMY_MODIFICATION_STATION",
        MaterialCompat.safe(XMaterial.GRINDSTONE),
        ThemeItemType.MACHINE,
        "Tinker's Modification Station",
        "Tools are augmented on the Modification",
        "station using the appropriate material.",
        "Once enough material has been added, the",
        "modification will level."
    );
    public static final RecipeType TYPE = new RecipeType(Keys.sfKey(Keys.WS_DUMMY_TOOL_TABLE), STACK);

    private DummyModificationStation() {
        throw new IllegalStateException("Utility class");
    }

}

