package io.github.sefiraat.slimetinker.listeners;

import io.github.sefiraat.slimetinker.utils.ItemUtils;
import io.github.sefiraat.slimetinker.utils.ThemeUtils;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import io.github.sefiraat.slimetinker.i18n.Messages;

public class DropItemListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        if (!Slimefun.getWorldSettingsService().isWorldEnabled(event.getPlayer().getWorld())) {
            return;
        }

        ItemStack itemStack = event.getItemDrop().getItemStack();
        if (ItemUtils.cannotDrop(itemStack)) {
            event.setCancelled(true);
            Messages.send(event.getPlayer(), ThemeUtils.WARNING, "item.cannot-drop", "This cannot be dropped.");
        }

    }
}

