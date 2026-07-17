package io.github.sefiraat.slimetinker.listeners;

import io.github.sefiraat.slimetinker.events.friend.EventChannels;
import io.github.sefiraat.slimetinker.events.friend.EventFriend;
import io.github.sefiraat.slimetinker.events.friend.TraitEventType;
import io.github.sefiraat.slimetinker.modifiers.Modifications;
import io.github.sefiraat.slimetinker.utils.BlockDataCompat;
import io.github.sefiraat.slimetinker.utils.BlockUtils;
import io.github.sefiraat.slimetinker.utils.Experience;
import io.github.sefiraat.slimetinker.utils.Ids;
import io.github.sefiraat.slimetinker.utils.ItemUtils;
import io.github.sefiraat.slimetinker.utils.Keys;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.sefiraat.slimetinker.utils.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import io.github.sefiraat.slimetinker.compat.Pdc;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockBreakListener implements Listener {

    public static final Map<Location, EventFriend> EVENT_FRIEND_MAP = new HashMap<>();

    @SuppressWarnings("unused")
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();
        ItemStack heldItem = player.getInventory().getItemInHand();
        Block block = event.getBlock();

        if (Slimefun.getIntegrations().isEventFaked(event)
            || Slimefun.getIntegrations().isCustomBlock(event.getBlock())
            || event.isCancelled()
            || isLockedTool(player, heldItem)
            || !BlockUtils.isValidBreakEvent(block, player)
        ) {
            return;
        }

        EventFriend friend = new EventFriend(player, TraitEventType.BLOCK_BREAK);

        friend.setBlock(block);
        friend.setDrops(block.getDrops(heldItem)); // Stores the event drops. All may not be dropped
        friend.setAddDrops(new ArrayList<>()); // Additional drops or substitutions for items from the main collection
        friend.setRemoveDrops(new ArrayList<>()); // Items to remove from the main collection if moved/reformed into the additional

        // Properties
        EventChannels.checkTool(friend);
        EventChannels.checkArmour(friend);

        if (friend.isActionTaken()) {

            if (friend.isCancelEvent()) {
                event.setCancelled(true);
                return;
            }

            // Mods
            modChecks(heldItem, block, friend.getAddDrops());

            // Settle
            EventChannels.settlePotionEffects(friend);

            if (ItemUtils.isTool(heldItem)) {
                if (shouldGrantExp(heldItem, event.getBlock())) { // Should grant exp (checks tool / material validity and the crop state)
                    Experience.addExp(heldItem, (int) Math.ceil(1 * friend.getToolExpMod()), event.getPlayer(), true);
                }
                if (event.getExpToDrop() > 0 && friend.isMetalCheck()) {
                    Experience.addExp(heldItem, (int) Math.ceil(event.getExpToDrop() / 10D), event.getPlayer(), true);
                    event.setExpToDrop(0);
                }
            }

            EVENT_FRIEND_MAP.put(block.getLocation(), friend);

        }
    }

    private boolean shouldGrantExp(ItemStack itemStack, Block block) {

        ItemMeta im = itemStack.getItemMeta();
        assert im != null;

        String toolType = Pdc.getString(im, Keys.TOOL_INFO_TOOL_TYPE.toString());
        assert toolType != null;

        // Hoe Stuff (Ageable and fully grown only)
        if (BlockDataCompat.isAgeable(block)) {
            if (BlockDataCompat.isFullyGrownAgeable(block)) {
                return toolType.equals(Ids.HOE);
            }
            return false;
        }

        // Block isn't in the block map, so no Exp
        if (!BlockMap.getMaterialMap().containsKey(block.getType())) {
            return false;
        }

        // Return toolType matches the stored one from the map
        return BlockMap.getMaterialMap().get(block.getType()).equals(toolType);

    }

    private void modChecks(ItemStack heldItem, Block block, Collection<ItemStack> addDrops) {
        modCheckLapis(heldItem, block, addDrops);
    }


    private void modCheckLapis(ItemStack heldItem, Block block, Collection<ItemStack> addDrops) {

        Map<String, Integer> modLevels = Modifications.getAllModLevels(heldItem);

        if (block.getDrops().isEmpty() || !modLevels.containsKey(MaterialCompat.safe(XMaterial.LAPIS_LAZULI).toString()) || heldItem.containsEnchantment(Enchantment.SILK_TOUCH)) { // There must be drops, the tools must have the lapis mod and the tool cannot have silk
            return;
        }

        int lapisLevel = modLevels.get(MaterialCompat.safe(XMaterial.LAPIS_LAZULI).toString());
        ItemStack dummyFortune = new ItemStack(MaterialCompat.safe(XMaterial.DIAMOND_PICKAXE));
        dummyFortune.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);

        List<Material> materialList = new ArrayList<>();

        for (ItemStack drop : block.getDrops()) {
            for (ItemStack dropFort : block.getDrops(dummyFortune)) {
                if (dropFort.getType() == drop.getType() && dropFort.getAmount() > drop.getAmount()) {
                    materialList.add(drop.getType());
                }
            }
        }

        for (ItemStack drop : block.getDrops(heldItem)) {
            if (materialList.contains(drop.getType())) {
                int additionalAmount = (int) Math.floor(drop.getAmount() * (lapisLevel * 0.1));
                if (additionalAmount > 0) {
                    ItemStack additionalDrop = new ItemStack(drop.getType());
                    additionalDrop.setAmount(additionalAmount);
                    addDrops.add(additionalDrop);
                    Location location = block.getLocation().clone().add(0.5, 0.5, 0.5);
                    Particle.DustOptions dustOptions = new Particle.DustOptions(Color.BLUE, 2);
                    block.getWorld().spawnParticle(Particle.REDSTONE, location, 10, 0.2, 0.2, 0.2, 0.5, dustOptions);
                }
            }
        }
    }

    public boolean isLockedTool(Player player, ItemStack itemStack) {
        SlimefunItem slimefunItem = SlimefunItem.getByItem(itemStack);
        return slimefunItem != null
            && !slimefunItem.canUse(player, false);
    }

}

