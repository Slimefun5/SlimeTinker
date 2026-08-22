package io.github.sefiraat.slimetinker.listeners;

import io.github.sefiraat.slimetinker.items.Materials;
import io.github.sefiraat.slimetinker.utils.BlockDataCompat;
import io.github.sefiraat.slimetinker.utils.BlockUtils;
import io.github.sefiraat.slimetinker.items.workstations.smeltery.TinkersSmeltery;
import io.github.thebusybiscuit.slimefun5.api.events.BlockPlacerPlaceEvent;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.core.multiblocks.MultiBlockAssembler;
import io.github.thebusybiscuit.slimefun5.core.guide.options.SlimefunGuideSettings;
import io.github.thebusybiscuit.slimefun5.core.services.sounds.SoundEffect;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.mrCookieSlime.Slimefun.api.BlockStorage;

import javax.annotation.Nonnull;

public class BlockPlaceListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!event.isCancelled() && !BlockDataCompat.isAgeable(event.getBlock())) { // Don't want crops to be marked, they are checked when broken against their age
            BlockUtils.getStateMap().put(event.getBlock().getLocation(), true);
        }
    }

    @SuppressWarnings("unused")
    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockPlacerPlace(BlockPlacerPlaceEvent event) {
        if (!event.isCancelled()) {
            BlockUtils.getStateMap().put(event.getBlock().getLocation(), true);
        }
    }

    /**
     * The smeltery is a bespoke, count-based multiblock (see {@code TinkersSmeltery#validateMultiblock}),
     * not a core {@code MultiBlockMachine}, so cheating and placing the controller alone never auto-built
     * the seared bricks/tank/spout around it the way core multiblocks do. Runs at MONITOR (after core's own
     * placement handling) so the controller's own placement/permission checks have already resolved, and
     * only wires the missing 8 cells through {@link MultiBlockAssembler#assembleAround} - the controller
     * itself is left alone, which is why its {@code layout} entry is {@code null}.
     */
    @SuppressWarnings("unused")
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onSmelteryControllerPlace(BlockPlaceEvent event) {
        SlimefunItem placed = SlimefunItem.getByItem(event.getItemInHand());

        if (placed == null || !Materials.SMELTERY_CONTROLLER.getItemId().equals(placed.getId())) {
            return;
        }

        Block controller = event.getBlockPlaced();

        Material brick = Materials.SEARED_BRICK_BLOCK.getType();
        String brickId = Materials.SEARED_BRICK_BLOCK.getItemId();

        Material[] layout = {
            brick, Materials.SPOUT.getType(), brick,
            brick, null, brick,
            brick, Materials.SEARED_TANK.getType(), brick
        };
        String[] customBlocks = {
            brickId, Materials.SPOUT.getItemId(), brickId,
            brickId, null, brickId,
            brickId, Materials.SEARED_TANK.getItemId(), brickId
        };

        MultiBlockAssembler.assembleAround(layout, customBlocks, controller, event.getPlayer());
    }

    /**
     * Announces a completed smeltery the same way core announces one of its own multiblocks, so building
     * the bespoke smeltery gives the player the same confirmation as building an Ore Crusher. Runs on any
     * component placement, not just the controller's, so a hand-built smeltery is announced too.
     */
    @SuppressWarnings("unused")
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onSmelteryComponentPlace(BlockPlaceEvent event) {
        Block placed = event.getBlockPlaced();
        Player player = event.getPlayer();

        // The controller can be any of the 9 cells away from the block just placed.
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    Block candidate = placed.getRelative(dx, dy, dz);
                    SlimefunItem controller = BlockStorage.check(candidate);

                    if (controller == null || !Materials.SMELTERY_CONTROLLER.getItemId().equals(controller.getId())) {
                        continue;
                    }

                    if (TinkersSmeltery.isComplete(candidate)) {
                        announceAssembled(player, controller);
                        return;
                    }
                }
            }
        }
    }

    private static void announceAssembled(@Nonnull Player player, @Nonnull SlimefunItem machine) {
        if (!SlimefunGuideSettings.hasMachineMessagesEnabled(player)) {
            return;
        }

        SoundEffect.ANCIENT_ALTAR_FINISH_SOUND.playFor(player);
        player.sendMessage(ChatColor.GREEN + "\u2714 Assembled: " + Slimefun.getItemTranslationService().getName(player, machine));
    }
}
