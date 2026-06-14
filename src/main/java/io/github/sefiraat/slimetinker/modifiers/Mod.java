package io.github.sefiraat.slimetinker.modifiers;

import io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey;

import java.util.Map;

public class Mod {

    private final Map<Integer, Integer> requirementMap;
    private final NamespacedKey levelKey;

    public Mod(Map<Integer, Integer> map, NamespacedKey levelKey) {
        requirementMap = map;
        this.levelKey = levelKey;
    }

    public Map<Integer, Integer> getRequirementMap() {
        return requirementMap;
    }

    public NamespacedKey getLevelKey() {
        return levelKey;
    }
}
