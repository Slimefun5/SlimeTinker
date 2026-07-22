package io.github.sefiraat.slimetinker.itemgroups;

import io.github.thebusybiscuit.slimefun5.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class DummyItemGroup extends ItemGroup {

    private final boolean hidden;

    public DummyItemGroup(NamespacedKey key, ItemStack item) {
        this(key, item, false);
    }

    public DummyItemGroup(NamespacedKey key, ItemStack item, boolean hidden) {
        super(new io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey(key.getNamespace(), key.getKey()), item);
        this.hidden = hidden;
    }

    @Override
    public boolean isHidden(@Nonnull Player p) {
        return hidden;
    }

}

