package io.github.sefiraat.slimetinker.i18n;

import javax.annotation.Nonnull;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Isolated pickup handler for {@link TinkerTranslationListener}. {@link EntityPickupItemEvent} only
 * exists on MC 1.12+, and Bukkit refuses to register an entire Listener whose handler references an
 * unloadable event type. Keeping this handler in its own class lets it be registered behind a
 * {@code Class.forName} guard while the translation listener's other handlers stay active on 1.8.
 */
public class TinkerPickupListener implements Listener {

    private final TinkerTranslationListener parent;

    public TinkerPickupListener(@Nonnull TinkerTranslationListener parent) {
        this.parent = parent;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPickup(@Nonnull EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            ItemStack stack = e.getItem().getItemStack();

            if (parent.apply(p, stack)) {
                e.getItem().setItemStack(stack);
            }
        }
    }
}
