package io.github.sefiraat.slimetinker.utils;

import javax.annotation.Nonnull;

import org.bukkit.Material;

import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;

/**
 * Resolves {@link XMaterial} constants to a {@link Material} that exists on the
 * running server. Keeps SlimeTinker loadable on legacy versions (e.g. 1.8) where
 * modern constants like {@code PLAYER_HEAD} or {@code *_CARPET} variants are absent.
 */
public final class MaterialCompat {

    private MaterialCompat() {
        throw new UnsupportedOperationException("Utility Class");
    }

    @Nonnull
    public static Material safe(@Nonnull XMaterial material) {
        Material resolved = material.parseMaterial();
        return resolved != null ? resolved : Material.STONE;
    }
}
