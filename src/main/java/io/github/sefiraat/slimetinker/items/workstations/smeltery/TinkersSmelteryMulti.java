package io.github.sefiraat.slimetinker.items.workstations.smeltery;

import io.github.sefiraat.slimetinker.items.Materials;
import io.github.thebusybiscuit.slimefun5.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun5.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun5.core.multiblocks.MultiBlockAssembler;
import io.github.thebusybiscuit.slimefun5.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun5.utils.compatibility.InventoryCompat;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * The guide entry for the Tinker's Smeltery, which builds the whole seared structure where you click.
 *
 * @implNote This used to be an {@code UnplaceableBlock}, so obtaining it through the guide's cheat mode
 *           handed the player an item that did nothing at all - the real machine is the separate
 *           {@code SMELTERY_CONTROLLER}. It now assembles the structure itself, the way a core
 *           {@code MultiBlockMachine} does, with the controller as the centre cell so
 *           {@code TinkersSmeltery}'s own block count sees a complete multiblock.
 */
public class TinkersSmelteryMulti extends SimpleSlimefunItem<ItemUseHandler> implements NotPlaceable {

    public TinkersSmelteryMulti(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();

            if (!e.getClickedBlock().isPresent()) {
                return;
            }

            Player player = e.getPlayer();

            // The bottom row rests on the clicked surface, so the controller lands one block above it.
            Block center = e.getClickedBlock().get().getRelative(e.getClickedFace()).getRelative(BlockFace.UP);

            if (MultiBlockAssembler.assembleAround(layout(), customBlocks(), center, player)) {
                InventoryCompat.consumeHeldItem(player, e.getHand(), 1, false);
            }
        };
    }

    @Nonnull
    private static Material[] layout() {
        Material brick = Materials.SEARED_BRICK_BLOCK.getType();

        return new Material[] {
            brick, Materials.SPOUT.getType(), brick,
            brick, Materials.SMELTERY_CONTROLLER.getType(), brick,
            brick, Materials.SEARED_TANK.getType(), brick
        };
    }

    @Nonnull
    private static String[] customBlocks() {
        String brickId = Materials.SEARED_BRICK_BLOCK.getItemId();

        return new String[] {
            brickId, Materials.SPOUT.getItemId(), brickId,
            brickId, Materials.SMELTERY_CONTROLLER.getItemId(), brickId,
            brickId, Materials.SEARED_TANK.getItemId(), brickId
        };
    }
}
