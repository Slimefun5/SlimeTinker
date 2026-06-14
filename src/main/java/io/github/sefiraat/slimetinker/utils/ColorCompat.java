package io.github.sefiraat.slimetinker.utils;

import java.lang.reflect.Method;
import java.util.Locale;

import javax.annotation.Nonnull;

import net.md_5.bungee.api.ChatColor;

/**
 * Version-safe replacement for {@link ChatColor#of(String)} (added in 1.16).
 *
 * On 1.16+ the hex colour is produced by reflectively invoking {@code ChatColor.of},
 * preserving the exact colour. On older servers (e.g. 1.8) where that method is
 * absent, the nearest of the 16 legacy {@link ChatColor} values is returned so the
 * plugin still loads and text stays sensibly coloured.
 */
public final class ColorCompat {

    private static final Method OF_METHOD = resolveOfMethod();

    private static final ChatColor[] LEGACY = {
        ChatColor.BLACK, ChatColor.DARK_BLUE, ChatColor.DARK_GREEN, ChatColor.DARK_AQUA,
        ChatColor.DARK_RED, ChatColor.DARK_PURPLE, ChatColor.GOLD, ChatColor.GRAY,
        ChatColor.DARK_GRAY, ChatColor.BLUE, ChatColor.GREEN, ChatColor.AQUA,
        ChatColor.RED, ChatColor.LIGHT_PURPLE, ChatColor.YELLOW, ChatColor.WHITE
    };

    // RGB values for the 16 legacy colours, index-aligned with LEGACY.
    private static final int[][] LEGACY_RGB = {
        {0, 0, 0}, {0, 0, 170}, {0, 170, 0}, {0, 170, 170},
        {170, 0, 0}, {170, 0, 170}, {255, 170, 0}, {170, 170, 170},
        {85, 85, 85}, {85, 85, 255}, {85, 255, 85}, {85, 255, 255},
        {255, 85, 85}, {255, 85, 255}, {255, 255, 85}, {255, 255, 255}
    };

    private ColorCompat() {
        throw new UnsupportedOperationException("Utility Class");
    }

    @Nonnull
    public static ChatColor of(@Nonnull String hex) {
        if (OF_METHOD != null) {
            try {
                return (ChatColor) OF_METHOD.invoke(null, hex);
            } catch (ReflectiveOperationException e) {
                // Fall through to the legacy approximation.
            }
        }
        return nearestLegacy(hex);
    }

    private static Method resolveOfMethod() {
        try {
            return ChatColor.class.getMethod("of", String.class);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    @Nonnull
    private static ChatColor nearestLegacy(@Nonnull String hex) {
        String value = hex.startsWith("#") ? hex.substring(1) : hex;
        int rgb;
        try {
            rgb = Integer.parseInt(value, 16);
        } catch (NumberFormatException e) {
            return ChatColor.WHITE;
        }

        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;

        int bestIndex = 0;
        long bestDistance = Long.MAX_VALUE;
        for (int i = 0; i < LEGACY_RGB.length; i++) {
            long dr = red - LEGACY_RGB[i][0];
            long dg = green - LEGACY_RGB[i][1];
            long db = blue - LEGACY_RGB[i][2];
            long distance = dr * dr + dg * dg + db * db;
            if (distance < bestDistance) {
                bestDistance = distance;
                bestIndex = i;
            }
        }
        return LEGACY[bestIndex];
    }
}
