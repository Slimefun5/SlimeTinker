package io.github.sefiraat.slimetinker.utils;

import java.lang.reflect.Method;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

/**
 * Version-safe wrapper around the {@code org.bukkit.block.data} package (added in
 * 1.13) and {@code org.bukkit.Tag} (added in 1.13). Every reference to those types
 * is reflective so this class — and its callers — load cleanly on 1.8, where the
 * APIs do not exist. On such legacy servers the operations degrade to no-ops or
 * sensible defaults rather than throwing.
 */
public final class BlockDataCompat {

    private static final Method GET_BLOCK_DATA = resolve(Block.class, "getBlockData");
    private static final Method SET_BLOCK_DATA = resolveSetBlockData();

    private static final Class<?> POWERABLE = forName("org.bukkit.block.data.Powerable");
    private static final Class<?> ANALOGUE_POWERABLE = forName("org.bukkit.block.data.AnaloguePowerable");
    private static final Class<?> LIGHTABLE = forName("org.bukkit.block.data.Lightable");
    private static final Class<?> AGEABLE = forName("org.bukkit.block.data.Ageable");
    private static final Class<?> LEVELLED = forName("org.bukkit.block.data.Levelled");
    private static final Class<?> DIRECTIONAL = forName("org.bukkit.block.data.Directional");

    private static final Object CROPS_TAG = resolveCropsTag();
    private static final Method TAG_IS_TAGGED = resolveTagIsTagged();

    private BlockDataCompat() {
        throw new UnsupportedOperationException("Utility Class");
    }

    public static void fakePower(@Nonnull Block block) {
        applyPower(block, true);
    }

    public static void fakeUnPower(@Nonnull Block block) {
        applyPower(block, false);
    }

    private static void applyPower(@Nonnull Block block, boolean powered) {
        Object data = blockData(block);
        if (data == null) {
            return;
        }
        try {
            if (POWERABLE != null && POWERABLE.isInstance(data)) {
                POWERABLE.getMethod("setPowered", boolean.class).invoke(data, powered);
            } else if (ANALOGUE_POWERABLE != null && ANALOGUE_POWERABLE.isInstance(data)) {
                ANALOGUE_POWERABLE.getMethod("setPower", int.class).invoke(data, powered ? 15 : 0);
            } else if (LIGHTABLE != null && LIGHTABLE.isInstance(data)) {
                LIGHTABLE.getMethod("setLit", boolean.class).invoke(data, powered);
            } else {
                return;
            }
            setBlockData(block, data);
        } catch (ReflectiveOperationException e) {
            // Legacy server: nothing to power.
        }
    }

    public static boolean isAgeable(@Nonnull Block block) {
        return AGEABLE != null && AGEABLE.isInstance(blockData(block));
    }

    public static boolean isFullyGrownAgeable(@Nonnull Block block) {
        Object data = blockData(block);
        if (AGEABLE == null || !AGEABLE.isInstance(data)) {
            return false;
        }
        try {
            int age = (int) AGEABLE.getMethod("getAge").invoke(data);
            int max = (int) AGEABLE.getMethod("getMaximumAge").invoke(data);
            return age == max;
        } catch (ReflectiveOperationException e) {
            return false;
        }
    }

    public static boolean isLavaSource(@Nonnull Block block) {
        Object data = blockData(block);
        if (LEVELLED == null || !LEVELLED.isInstance(data)) {
            return false;
        }
        try {
            return (int) LEVELLED.getMethod("getLevel").invoke(data) == 0;
        } catch (ReflectiveOperationException e) {
            return false;
        }
    }

    public static void setDirectionalFacing(@Nonnull Block block, @Nonnull BlockFace face) {
        Object data = blockData(block);
        if (DIRECTIONAL == null || !DIRECTIONAL.isInstance(data)) {
            return;
        }
        try {
            DIRECTIONAL.getMethod("setFacing", BlockFace.class).invoke(data, face);
            setBlockData(block, data);
        } catch (ReflectiveOperationException e) {
            // Legacy server: facing is implied by the placed block.
        }
    }

    public static boolean isCrop(@Nonnull Material material) {
        if (CROPS_TAG == null || TAG_IS_TAGGED == null) {
            return false;
        }
        try {
            return (boolean) TAG_IS_TAGGED.invoke(CROPS_TAG, material);
        } catch (ReflectiveOperationException e) {
            return false;
        }
    }

    private static Object blockData(@Nonnull Block block) {
        if (GET_BLOCK_DATA == null) {
            return null;
        }
        try {
            return GET_BLOCK_DATA.invoke(block);
        } catch (ReflectiveOperationException e) {
            return null;
        }
    }

    private static void setBlockData(@Nonnull Block block, @Nonnull Object data) throws ReflectiveOperationException {
        if (SET_BLOCK_DATA != null) {
            SET_BLOCK_DATA.invoke(block, data);
        }
    }

    private static Method resolve(Class<?> owner, String name) {
        try {
            return owner.getMethod(name);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private static Method resolveSetBlockData() {
        Class<?> blockData = forName("org.bukkit.block.data.BlockData");
        if (blockData == null) {
            return null;
        }
        try {
            return Block.class.getMethod("setBlockData", blockData);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private static Class<?> forName(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private static Object resolveCropsTag() {
        try {
            return Class.forName("org.bukkit.Tag").getField("CROPS").get(null);
        } catch (ReflectiveOperationException e) {
            return null;
        }
    }

    private static Method resolveTagIsTagged() {
        try {
            return Class.forName("org.bukkit.Tag").getMethod("isTagged", Object.class);
        } catch (ReflectiveOperationException e) {
            return null;
        }
    }
}
