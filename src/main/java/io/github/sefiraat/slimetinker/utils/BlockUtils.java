package io.github.sefiraat.slimetinker.utils;

import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun5.libraries.dough.protection.Interaction;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BlockUtils {

    private BlockUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final Map<Location, Boolean> STATE_MAP = new HashMap<>();

    /**
     * {@code org.bukkit.block.TileState}, or {@code null} on versions predating it (pre-1.14).
     *
     * @implNote Resolved reflectively rather than referenced directly: naming the class in a method
     *           body (even behind an {@code instanceof}) makes the verifier load it eagerly at
     *           class-load, throwing {@link NoClassDefFoundError} on 1.8. Reflection lets the check
     *           simply never match where the class is absent.
     */
    private static final Class<?> TILE_STATE_CLASS = resolveTileStateClass();

    private static Class<?> resolveTileStateClass() {
        try {
            return Class.forName("org.bukkit.block.TileState");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private static boolean isTileState(BlockState blockState) {
        return TILE_STATE_CLASS != null && TILE_STATE_CLASS.isInstance(blockState);
    }

    public static boolean isValidBreakEvent(Block block, Player player) {
        return !isPlaced(block)
            && !BlockStorage.hasBlockInfo(block)
            && !isTileState(block.getState())
            && Slimefun.getProtectionManager().hasPermission(player, block, Interaction.BREAK_BLOCK);
    }

    public static boolean isPlaced(Block block) {
        return STATE_MAP.getOrDefault(block.getLocation(), false);
    }

    /**
     * Credits : https://www.spigotmc.org/threads/getting-the-blockface-of-a-targeted-block.319181/
     * Gets the BlockFace of the block the player is currently targeting.
     *
     * @param player the player's whos targeted blocks BlockFace is to be checked.
     * @return the BlockFace of the targeted block, or null if the targeted block is non-occluding.
     */
    public static BlockFace getTargetedBlockFace(Player player) {
        List<Block> lastTwoTargetBlocks = player.getLastTwoTargetBlocks(null, 100);
        if (lastTwoTargetBlocks.size() != 2 || !lastTwoTargetBlocks.get(1).getType().isOccluding()) return null;
        Block targetBlock = lastTwoTargetBlocks.get(1);
        Block adjacentBlock = lastTwoTargetBlocks.get(0);
        return targetBlock.getFace(adjacentBlock);
    }

    public static void fakePower(Block block) {
        BlockDataCompat.fakePower(block);
    }

    public static void fakeUnPower(Block block) {
        BlockDataCompat.fakeUnPower(block);
    }

    public static Map<Location, Boolean> getStateMap() {
        return STATE_MAP;
    }
}

