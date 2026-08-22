package io.github.sefiraat.slimetinker.compat;

import java.util.Optional;

import io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey;
import io.github.thebusybiscuit.slimefun5.utils.compatibility.PdcCompat;

/**
 * Load-safe PDC facade. dough's {@code PersistentDataAPI} cannot load on MC 1.8 (its 1.14+
 * {@code PersistentDataType} references break class verification), so every access goes through a
 * version-tolerant layer instead. Keys are the {@code namespace:key} strings this addon already uses.
 *
 * @implNote Delegates to core's {@code PdcCompat}, NOT to dough's {@code VersionedPdc}. VersionedPdc
 *           silently fails on 26.x - a write followed immediately by a read of the same
 *           {@code ItemMeta} returned null - which meant no tinker item had a readable material, class or
 *           type: every workstation refused to combine anything, and the resolver could not name an
 *           assembled tool. {@code PdcCompat} is the layer that was fixed for non-public craftbukkit
 *           classes on 26.x and falls back to item NBT below 1.14.
 */
public final class Pdc {

    private Pdc() {}

    private static NamespacedKey key(String k) {
        return NamespacedKey.fromString(k);
    }

    public static void setString(Object h, String k, String v) { PdcCompat.setString(h, key(k), v); }
    public static String getString(Object h, String k) { return PdcCompat.getString(h, key(k)); }
    public static String getString(Object h, String k, String def) { String v = PdcCompat.getString(h, key(k)); return v != null ? v : def; }
    public static Optional<String> getOptionalString(Object h, String k) { return Optional.ofNullable(PdcCompat.getString(h, key(k))); }
    public static boolean hasString(Object h, String k) { return PdcCompat.has(h, key(k), "STRING"); }
    public static void setInt(Object h, String k, int v) { PdcCompat.setInt(h, key(k), v); }
    public static int getInt(Object h, String k) { return PdcCompat.getInt(h, key(k), -1); }
    public static int getInt(Object h, String k, int def) { return PdcCompat.getInt(h, key(k), def); }
    public static boolean hasInt(Object h, String k) { return PdcCompat.has(h, key(k), "INTEGER"); }
    public static void setLong(Object h, String k, long v) { PdcCompat.set(h, key(k), "LONG", v); }
    public static long getLong(Object h, String k) { return getLong(h, k, -1L); }
    public static long getLong(Object h, String k, long def) { Object v = PdcCompat.getOrDefault(h, key(k), "LONG", def); return v instanceof Number ? ((Number) v).longValue() : def; }
    public static void setDouble(Object h, String k, double v) { PdcCompat.set(h, key(k), "DOUBLE", v); }
    public static double getDouble(Object h, String k) { return getDouble(h, k, -1D); }
    public static double getDouble(Object h, String k, double def) { Object v = PdcCompat.getOrDefault(h, key(k), "DOUBLE", def); return v instanceof Number ? ((Number) v).doubleValue() : def; }
    public static void setBoolean(Object h, String k, boolean v) { PdcCompat.setByte(h, key(k), (byte) (v ? 1 : 0)); }
    public static boolean getBoolean(Object h, String k) { return PdcCompat.getByte(h, key(k)) != 0; }
    public static void remove(Object h, String k) { PdcCompat.remove(h, key(k)); }
}
