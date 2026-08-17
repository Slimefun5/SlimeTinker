package io.github.sefiraat.slimetinker.utils;

import io.github.sefiraat.slimetinker.items.Guide;
import io.github.sefiraat.slimetinker.modifiers.Modifications;
import io.github.sefiraat.slimetinker.compat.Pdc;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public final class Experience {

    private Experience() {
        throw new IllegalStateException("Utility class");
    }

    public static final double EXP_LEVEL_BASE = 100;
    public static final double EXP_GROWTH = 1.3;

    public static void setupExpNew(ItemMeta im) {
        Pdc.setInt(im, Keys.ST_EXP_CURRENT.toString(), 0);
        Pdc.setDouble(im, Keys.ST_EXP_REQUIRED.toString(), EXP_LEVEL_BASE);
        Pdc.setInt(im, Keys.ST_LEVEL.toString(), 0);
        Pdc.setInt(im, Keys.ST_MOD_SLOTS.toString(), 0);
    }

    public static void addExp(ItemStack itemStack, int amount, Player player, boolean tool) {

        ItemMeta im = itemStack.getItemMeta();
        assert im != null;

        if (copperChecks(itemStack, player, amount)) {
            return;
        }

        int currentExp = Pdc.getInt(im, Keys.ST_EXP_CURRENT.toString(), 0);
        double expRequired = Pdc.getDouble(im, Keys.ST_EXP_REQUIRED.toString(), 0);
        int level = Pdc.getInt(im, Keys.ST_LEVEL.toString(), 0);
        int modSlots = Pdc.getInt(im, Keys.ST_MOD_SLOTS.toString(), 0);
        int newExp = 0;

        Map<String, Integer> modLevels = Modifications.getAllModLevels(itemStack);

        if (modLevels.containsKey(MaterialCompat.safe(XMaterial.EMERALD).toString())) {
            int eLevel = modLevels.get(MaterialCompat.safe(XMaterial.EMERALD).toString());
            if (eLevel > 0) amount = tool ? amount + eLevel : (int) Math.ceil(amount * (1 + eLevel * 0.1));
        }

        if ((currentExp + amount) >= expRequired) {

            level++;
            modSlots++;
            expRequired = (expRequired * EXP_GROWTH);
            promoteMaterial(itemStack, level, player);
            player.sendMessage(ThemeUtils.SUCCESS + "Your Tinker's tool has leveled up! +1 Modifier Slot");

            silverChecks(itemStack, im, player);

        } else {
            newExp = currentExp + amount;
        }

        Pdc.setInt(im, Keys.ST_EXP_CURRENT.toString(), newExp);
        Pdc.setDouble(im, Keys.ST_EXP_REQUIRED.toString(), expRequired);
        Pdc.setInt(im, Keys.ST_LEVEL.toString(), level);
        Pdc.setInt(im, Keys.ST_MOD_SLOTS.toString(), modSlots);

        itemStack.setItemMeta(im);

        ItemUtils.rebuildTinkerLore(itemStack);

    }

    private static void promoteMaterial(ItemStack itemStack, int level, Player player) {
        // Already at max promotion
        if (level > (Guide.LEVEL_TOOLS_NETHERITE + 1)) {
            return;
        }

        ItemMeta im = itemStack.getItemMeta();
        String type;
        if (ItemUtils.isTool(itemStack)) {
            type = Pdc.getString(im, Keys.TOOL_INFO_TOOL_TYPE.toString());
        } else if (ItemUtils.isArmour(itemStack)) {
            type = Pdc.getString(im, Keys.ARMOUR_INFO_ARMOUR_TYPE.toString());
        } else {
            throw new IllegalArgumentException("Trying to promote something that isn't armour or a tool!");
        }

        if (Guide.getGrowthMap().get(type).containsKey(level)) {
            itemStack.setType(Guide.getGrowthMap().get(type).get(level));
            ItemUtils.repairItem(itemStack);
            player.sendMessage(ThemeUtils.SUCCESS + "Your tool has been promoted!");
        }

    }

    private static boolean copperChecks(ItemStack itemStack, Player player, int amount) {
        if (ItemUtils.isConductive1(itemStack)) {
            player.giveExp(amount);
            return true;
        } else if (ItemUtils.isConductive2(itemStack)) {
            player.giveExp((int) Math.ceil(amount * 1.5));
            return true;
        }
        return false;
    }

    private static void silverChecks(ItemStack itemStack, ItemMeta im, Player player) {
        if (ItemUtils.isEnchanting(itemStack)) {
            int number = ItemUtils.isEnchanting2(itemStack) ? 3 : 1;
            int amount = ThreadLocalRandom.current().nextInt(1, number + 1);
            for (int i = 0; i < amount; i++) {
                ItemUtils.incrementRandomEnchant(itemStack, im);
            }
            player.sendMessage(ThemeUtils.SUCCESS + "It also gained [" + amount + "] random enchantment(s)! Hope it's good :>");
        }
    }
}

