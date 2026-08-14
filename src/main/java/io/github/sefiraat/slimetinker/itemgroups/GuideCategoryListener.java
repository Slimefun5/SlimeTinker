package io.github.sefiraat.slimetinker.itemgroups;

import io.github.thebusybiscuit.slimefun5.api.events.SlimefunItemRegistryFinalizedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Runs {@link ItemGroups#categorise()} once every addon has finished registering items.
 *
 * @implNote Core only links a {@link io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem} into its
 *           {@link io.github.thebusybiscuit.slimefun5.api.items.ItemGroup} via {@code SlimefunItem#load()},
 *           which runs on the first server tick after every plugin has enabled (see core's
 *           {@code PostSetup#loadItems}). Calling {@link ItemGroups#categorise()} directly from
 *           {@code onEnable()} sees every group still empty and sets no guide type at all, so it must wait
 *           for this event instead.
 */
public final class GuideCategoryListener implements Listener {

    @EventHandler
    public void onItemsFinalized(SlimefunItemRegistryFinalizedEvent event) {
        ItemGroups.categorise();
    }
}
