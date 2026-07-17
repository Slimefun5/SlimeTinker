package io.github.sefiraat.slimetinker.listeners;

import io.github.sefiraat.slimetinker.events.friend.EventFriend;
import io.github.sefiraat.slimetinker.utils.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

/**
 * Isolated drop handler for {@link BlockBreakListener}. {@link BlockDropItemEvent} only exists on
 * MC 1.13+, and Bukkit refuses to register an entire Listener whose handler references an unloadable
 * event type - which previously took the core {@code onBlockBreak} drop-tracking down with it on 1.8.
 * Keeping this handler in its own class lets it be registered behind a {@code Class.forName} guard
 * while {@link BlockBreakListener} keeps working on every version.
 */
public class BlockDropListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onDrops(BlockDropItemEvent event) {
        Block block = event.getBlock();
        Location location = block.getLocation();
        EventFriend friend = BlockBreakListener.EVENT_FRIEND_MAP.remove(location);

        if (friend != null) {
            event.getItems().clear();
            Player player = friend.getPlayer();
            for (ItemStack i : friend.getDrops()) { // Drop items in original collection not flagged for removal
                if (friend.getRemoveDrops().contains(i) || i.getType() == MaterialCompat.safe(XMaterial.AIR)) {
                    continue;
                }
                if (friend.isBlocksIntoInv()) {
                    Map<Integer, ItemStack> remainingItems = player.getInventory().addItem(i);
                    for (ItemStack i2 : remainingItems.values()) {
                        block.getWorld().dropItem(block.getLocation().clone().add(0.5, 0.5, 0.5), i2);
                    }
                    continue;
                }
                block.getWorld().dropItem(block.getLocation().clone().add(0.5, 0.5, 0.5), i);
            }

            for (ItemStack i : friend.getAddDrops()) { // Then the additional items collection - no removals
                if (friend.isBlocksIntoInv()) {
                    Map<Integer, ItemStack> remainingItems = player.getInventory().addItem(i);
                    for (ItemStack i2 : remainingItems.values()) {
                        block.getWorld().dropItem(block.getLocation().clone().add(0.5, 0.5, 0.5), i2);
                    }
                    continue;
                }
                block.getWorld().dropItem(block.getLocation().clone().add(0.5, 0.5, 0.5), i);
            }
        }
    }
}
