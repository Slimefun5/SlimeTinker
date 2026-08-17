package io.github.sefiraat.slimetinker.items.workstations.smeltery;

import io.github.sefiraat.slimetinker.items.Materials;
import io.github.thebusybiscuit.slimefun5.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun5.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun5.core.multiblocks.MultiBlockAssembler;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun5.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun5.utils.compatibility.InventoryCompat;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
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
 *           {@code MultiBlockMachine} does.
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

            // MultiBlockAssembler skips the centre cell entirely (its layout entry is null), so unlike the
            // other eight this one has to be bounds-checked here.
            if (!center.isEmpty()) {
                Slimefun.getLocalization().sendMessage(player, "messages.multiblock-assembler.no-space", true);
                return;
            }

            if (!MultiBlockAssembler.assembleAround(layout(), customBlocks(), center, player)) {
                return;
            }

            placeController(center);
            InventoryCompat.consumeHeldItem(player, e.getHand(), 1, false);
        };
    }

    /**
     * Registers the controller in the centre cell the way a hand placement would.
     *
     * @implNote Deliberately not routed through {@link MultiBlockAssembler}: {@code MenuBlock} declares a
     *           {@code BlockPlaceHandler(false)}, so the assembler's "may this be placed automatically?"
     *           gate rejects the controller and aborts the whole structure with "required block is not
     *           available". That gate is about a BlockPlacer dropping machines, which is not what this is.
     *           {@code TinkersSmeltery} does no work in {@code onPlace} - its cache and menu-opening
     *           handler are wired in {@code onNewInstance}, which BlockStorage drives - so nothing is
     *           skipped by registering the block directly.
     */
    private static void placeController(@Nonnull Block center) {
        SlimefunItem controller = SlimefunItem.getById(Materials.SMELTERY_CONTROLLER.getItemId());

        if (controller == null) {
            return;
        }

        center.setType(Materials.SMELTERY_CONTROLLER.getType());

        if (Slimefun.getBlockDataService().isTileEntity(center.getType())) {
            Slimefun.getBlockDataService().setBlockData(center, controller.getId());
        }

        BlockStorage.addBlockInfo(center, "id", controller.getId(), true);
    }

    @Nonnull
    private static Material[] layout() {
        Material brick = Materials.SEARED_BRICK_BLOCK.getType();

        return new Material[] {
            brick, Materials.SPOUT.getType(), brick,
            brick, null, brick,
            brick, Materials.SEARED_TANK.getType(), brick
        };
    }

    @Nonnull
    private static String[] customBlocks() {
        String brickId = Materials.SEARED_BRICK_BLOCK.getItemId();

        return new String[] {
            brickId, Materials.SPOUT.getItemId(), brickId,
            brickId, null, brickId,
            brickId, Materials.SEARED_TANK.getItemId(), brickId
        };
    }
}
