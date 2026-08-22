package io.github.sefiraat.slimetinker.compat;

import java.util.Locale;
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

    /**
     * Bukkit only accepts {@code [a-z0-9._-]} in a key, but this addon's keys are historically CamelCase
     * ({@code ST_Material}). Normalising here rather than renaming every constant keeps all call sites and
     * the {@link io.github.sefiraat.slimetinker.utils.Keys} names untouched.
     *
     * @implNote Without this, nothing persisted on 1.14+: the fork's NamespacedKey shim accepts an invalid
     *           key silently, {@code BukkitKeys.toBukkit} then returns null (Bukkit's own fromString
     *           rejects it) and {@code PdcCompat} falls through to its 1.8-1.13 item-NBT path, which does
     *           not persist on a modern server. Writing and immediately reading the same ItemMeta returned
     *           null, so no tinker item had a readable material/class/type and no workstation could combine
     *           anything. Proven on 26.2: the same round-trip succeeds with a lowercase key.
     */
    private static NamespacedKey key(String k) {
        NamespacedKey parsed = NamespacedKey.fromString(k);

        if (parsed == null) {
            return null;
        }

        return new NamespacedKey(parsed.getNamespace().toLowerCase(Locale.ROOT), parsed.getKey().toLowerCase(Locale.ROOT));
    }

    /**
     * Reads {@code k}, falling back to the pre-normalisation CamelCase key.
     *
     * @implNote 1.8-1.13 stored under the raw CamelCase string (the item-NBT path never went through
     *           Bukkit's key validation), so saves from a legacy server still carry data there. Modern
     *           servers never managed to store anything under it, so this only ever helps.
     */
    private static String readWithLegacyFallback(Object h, String k) {
        String value = PdcCompat.getString(h, key(k));
        return value != null ? value : PdcCompat.getString(h, NamespacedKey.fromString(k));
    }

    public static void setString(Object h, String k, String v) { PdcCompat.setString(h, key(k), v); }
    public static String getString(Object h, String k) { return readWithLegacyFallback(h, k); }
    public static String getString(Object h, String k, String def) { String v = readWithLegacyFallback(h, k); return v != null ? v : def; }
    public static Optional<String> getOptionalString(Object h, String k) { return Optional.ofNullable(readWithLegacyFallback(h, k)); }
    public static boolean hasString(Object h, String k) { return readWithLegacyFallback(h, k) != null; }
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
