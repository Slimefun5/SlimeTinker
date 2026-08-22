package io.github.sefiraat.slimetinker.items.workstations.smeltery;

import io.github.mooy1.infinitylib.machines.TickingMenuBlock;
import io.github.sefiraat.slimetinker.i18n.TinkerLang;
import io.github.sefiraat.slimetinker.items.Materials;
import io.github.sefiraat.slimetinker.utils.GUIItems;
import io.github.sefiraat.slimetinker.utils.ThemeUtils;
import io.github.thebusybiscuit.slimefun5.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.core.multiblocks.MultiBlock;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun5.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class TinkersSmeltery extends TickingMenuBlock {

    protected static final int[] CAST_STORE_SLOTS = {36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};
    protected static final int INPUT_SLOT = 10;
    protected static final int CAST_SLOT = 13;
    protected static final int OUTPUT_SLOT = 16;
    protected static final int PURGE_BUTTON = 32;
    protected static final int ALLOY_BUTTON = 33;
    protected static final int POUR_BUTTON = 34;
    protected static final int LAVA_INFO = 28;
    protected static final int METAL_INFO = 30;
    private static final int[] BACKGROUND_SLOTS = {27, 29, 31, 35};
    private static final int[] BACKGROUND_INPUT_SLOTS = {0, 1, 2, 9, 11, 18, 19, 20};
    private static final int[] BACKGROUND_CAST_SLOTS = {3, 4, 5, 12, 14, 21, 22, 23};
    private static final int[] BACKGROUND_OUTPUT_SLOTS = {6, 7, 8, 15, 17, 24, 25, 26};
    private final Map<Location, TinkersSmelteryCache> caches = new HashMap<>();

    public TinkersSmeltery(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    protected void tick(Block block, BlockMenu blockMenu) {
        TinkersSmelteryCache cache = TinkersSmeltery.this.caches.get(block.getLocation());
        if (cache != null) {
            cache.process(false);
        }
    }

    @Override
    protected void setup(BlockMenuPreset blockMenuPreset) {

        blockMenuPreset.setSize(54);

        blockMenuPreset.drawBackground(ChestMenuUtils.getBackground(), BACKGROUND_SLOTS);
        blockMenuPreset.drawBackground(GUIItems.MENU_BACKGROUND_INPUT, BACKGROUND_INPUT_SLOTS);
        blockMenuPreset.drawBackground(GUIItems.MENU_BACKGROUND_OUTPUT, BACKGROUND_OUTPUT_SLOTS);
        blockMenuPreset.drawBackground(GUIItems.MENU_BACKGROUND_CAST, BACKGROUND_CAST_SLOTS);

        blockMenuPreset.addItem(LAVA_INFO, GUIItems.menuLavaInfo(0, 0, TinkersSmelteryCache.LAVA_MAX));
        blockMenuPreset.addItem(METAL_INFO, GUIItems.menuMetalInfo(0, 0, TinkersSmelteryCache.METALS_MAX, null));

        blockMenuPreset.addItem(PURGE_BUTTON, GUIItems.MENU_PURGE);
        blockMenuPreset.addMenuClickHandler(PURGE_BUTTON, (player, i, itemStack, clickAction) -> false);

        blockMenuPreset.addItem(ALLOY_BUTTON, GUIItems.MENU_ALLOY);
        blockMenuPreset.addMenuClickHandler(ALLOY_BUTTON, (player, i, itemStack, clickAction) -> false);

        blockMenuPreset.addItem(POUR_BUTTON, GUIItems.MENU_POUR);
        blockMenuPreset.addMenuClickHandler(POUR_BUTTON, (player, i, itemStack, clickAction) -> false);

    }

    @Override
    protected int[] getInputSlots() {
        return new int[]{INPUT_SLOT};
    }

    @Override
    protected int[] getOutputSlots() {
        return new int[]{OUTPUT_SLOT};
    }

    @Override
    protected void onBreak(@Nonnull BlockBreakEvent event, @Nonnull BlockMenu blockMenu) {
        super.onBreak(event, blockMenu);
        Location location = blockMenu.getLocation();
        TinkersSmelteryCache simpleInventoryCache = caches.remove(location);
        if (simpleInventoryCache != null) {
            simpleInventoryCache.kill(location);
        }
        blockMenu.dropItems(location, INPUT_SLOT);
        blockMenu.dropItems(location, CAST_STORE_SLOTS);
    }

    @Override
    protected void onNewInstance(@Nonnull BlockMenu menu, @Nonnull Block b) {
        super.onNewInstance(menu, b);
        TinkersSmelteryCache cache = new TinkersSmelteryCache(menu);

        String lavaLevel = BlockStorage.getLocationInfo(menu.getLocation(), TinkersSmelteryCache.LAVA_LEVEL_BS);
        if (lavaLevel != null) {
            cache.setLevelLava(Integer.parseInt(lavaLevel));
        }

        Config c = BlockStorage.getLocationInfo(menu.getLocation());

        for (String key : c.getKeys()) {
            if (key.startsWith(TinkersSmelteryCache.METAL_LEVEL_PREFIX)) {
                String id = key.replace(TinkersSmelteryCache.METAL_LEVEL_PREFIX, "");
                int amount = Integer.parseInt(c.getString(key));
                cache.getTankContent().put(id, amount);
            }
        }

        caches.put(b.getLocation(), cache);
    }

    /**
     * Refuses to open until the smeltery is built, so an incomplete structure is rejected before the menu
     * exists rather than flashing one open and closing it a tick later.
     */
    @Override
    protected boolean canOpen(Block b, Player p) {
        return isComplete(b);
    }

    @Override
    protected String getAccessDenialMessage(Block b, Player p) {
        return ThemeUtils.WARNING + TinkerLang.message(p, "smeltery-incomplete");
    }

    /**
     * Registers this smeltery's structure with core, so {@code /sf owner} and core's assembled announcement
     * recognise it the way they recognise a {@link MultiBlock} machine - without giving up the menu and tank
     * that a {@code MultiBlockMachine} could not have.
     *
     * @implNote Core's interact listener is registered before its multiblock listener, which is what lets the
     *           menu still open even though a structure match cancels the interact event.
     */
    public void registerStructure() {
        Slimefun.getRegistry().getMultiBlocks().add(new SmelteryStructure(this));
    }

    /**
     * The smeltery's structure as core sees it, validated by {@link TinkersSmeltery#isComplete(Block)} rather
     * than by a fixed arrangement of cells.
     *
     * @implNote Core matches a literal cell layout whereas the smeltery only counts blocks, so its tank and
     *           spout may sit in any ring cell. Deferring both matching methods to {@code isComplete} keeps
     *           ONE registration where enumerating layouts would need 29, and leaves core and the machine
     *           with a single shared definition of "built". The layout handed to {@code super} is never
     *           matched against, but it must still name every component {@link Material} because
     *           {@code MultiBlockListener} pre-filters candidate structures on it.
     */
    private static final class SmelteryStructure extends MultiBlock {

        private SmelteryStructure(TinkersSmeltery smeltery) {
            super(smeltery, componentMaterials(), BlockFace.SELF);
        }

        private static Material[] componentMaterials() {
            Material bricks = Materials.SEARED_BRICK_BLOCK.getType();

            return new Material[]{
                bricks, Materials.SEARED_TANK.getType(), bricks,
                bricks, Materials.SMELTERY_CONTROLLER.getType(), bricks,
                bricks, Materials.SPOUT.getType(), bricks
            };
        }

        @Override
        public boolean matches(@Nonnull Block center) {
            return isComplete(center);
        }

        @Override
        public boolean containsBlock(@Nonnull Block center, @Nonnull Block placed) {
            if (!placed.getWorld().equals(center.getWorld())) {
                return false;
            }

            int offsetX = placed.getX() - center.getX();
            int offsetY = placed.getY() - center.getY();
            int offsetZ = placed.getZ() - center.getZ();

            if (Math.abs(offsetX) > 1 || Math.abs(offsetY) > 1 || Math.abs(offsetZ) > 1) {
                return false;
            }

            // The structure occupies a single vertical plane, so one horizontal offset is always zero.
            return offsetX == 0 || offsetZ == 0;
        }
    }

    /**
     * Whether the smeltery around {@code controller} is built correctly.
     *
     * @implNote Count-based rather than a fixed layout: the structure is valid in either vertical plane, so
     *           the blocks in each are tallied and compared against the required set.
     *
     * @param controller
     *            The smeltery controller block
     *
     * @return Whether the surrounding structure is complete
     */
    public static boolean isComplete(@Nonnull Block controller) {
        Map<String, Integer> blockMapMaster = new HashMap<>();

        blockMapMaster.put(Materials.SEARED_BRICK_BLOCK.getItemId(), 6);
        blockMapMaster.put(Materials.SEARED_TANK.getItemId(), 1);
        blockMapMaster.put(Materials.SPOUT.getItemId(), 1);
        blockMapMaster.put(Materials.SMELTERY_CONTROLLER.getItemId(), 1);

        return blockMapMaster.equals(getBlockMapXY(controller)) || blockMapMaster.equals(getBlockMapZY(controller));
    }

    private static Map<String, Integer> getBlockMapXY(Block b) {
        Map<String, Integer> blockMapXY = new HashMap<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                String id = BlockStorage.getLocationInfo(b.getRelative(x, y, 0).getLocation(), "id");
                if (id != null) {
                    if (blockMapXY.containsKey(id)) {
                        blockMapXY.put(id, blockMapXY.get(id) + 1);
                    } else {
                        blockMapXY.put(id, 1);
                    }
                }
            }
        }
        return blockMapXY;
    }

    private static Map<String, Integer> getBlockMapZY(Block b) {
        Map<String, Integer> blockMapZY = new HashMap<>();
        for (int z = -1; z <= 1; z++) {
            for (int y = -1; y <= 1; y++) {
                String id = BlockStorage.getLocationInfo(b.getRelative(0, y, z).getLocation(), "id");
                if (id != null) {
                    if (blockMapZY.containsKey(id)) {
                        blockMapZY.put(id, blockMapZY.get(id) + 1);
                    } else {
                        blockMapZY.put(id, 1);
                    }
                }
            }
        }
        return blockMapZY;
    }

}

