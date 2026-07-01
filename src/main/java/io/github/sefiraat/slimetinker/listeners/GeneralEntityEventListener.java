package io.github.sefiraat.slimetinker.listeners;

import io.github.sefiraat.slimetinker.utils.Keys;
import io.github.sefiraat.slimetinker.compat.Pdc;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.EntityTeleportEvent;

public class GeneralEntityEventListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    public void onEntityTeleport(EntityTeleportEvent event) {
        if (Pdc.hasString(event.getEntity(), Keys.TOOL_FLAG_TELEPORT.toString())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onChangeTarget(EntityTargetLivingEntityEvent event) {
        String id = Pdc.getString(event.getEntity(), Keys.ARMOUR_HAPPY_PIGLIN.toString());
        if (event.getTarget() instanceof Player) {
            Player p = (Player) event.getTarget();
            if (id != null && id.equals(p.getUniqueId().toString())) {
                event.setCancelled(true);
            }
        }
    }
}

