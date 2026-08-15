package io.github.sefiraat.slimetinker.listeners;

import io.github.sefiraat.slimetinker.items.Materials;
import io.github.sefiraat.slimetinker.utils.BlockDataCompat;
import io.github.sefiraat.slimetinker.utils.BlockUtils;
import io.github.thebusybiscuit.slimefun5.api.events.BlockPlacerPlaceEvent;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.core.multiblocks.MultiBlockAssembler;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

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
}

