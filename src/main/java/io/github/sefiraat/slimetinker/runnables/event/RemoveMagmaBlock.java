package io.github.sefiraat.slimetinker.runnables.event;

import io.github.sefiraat.slimetinker.utils.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class RemoveMagmaBlock extends BukkitRunnable {

    private final Block block;

    public RemoveMagmaBlock(Block block) {
        this.block = block;
    }

    @Override
    public void run() {
        block.setType(MaterialCompat.safe(XMaterial.LAVA));
    }
}
