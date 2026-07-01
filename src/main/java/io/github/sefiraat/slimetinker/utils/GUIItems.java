package io.github.sefiraat.slimetinker.utils;

import io.github.sefiraat.slimetinker.items.tinkermaterials.TinkerMaterialManager;
import io.github.thebusybiscuit.slimefun5.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun5.utils.compatibility.VersionedPlayerHead;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class GUIItems {

    private GUIItems() {
        throw new IllegalStateException("Utility class");
    }

    public static final ItemStack MENU_BACKGROUND_INPUT = new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.LIGHT_BLUE_STAINED_GLASS_PANE), ChatColor.BLUE + "Input",
        " "
    ).item();

    public static final ItemStack MENU_BACKGROUND_OUTPUT = new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.ORANGE_STAINED_GLASS_PANE), ChatColor.RED + "Output",
        " "
    ).item();

    public static final ItemStack MENU_BACKGROUND_CAST = new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.LIME_STAINED_GLASS_PANE), ChatColor.GREEN + "Cast/Die",
        " "
    ).item();

    public static final ItemStack MENU_PREVIEW = new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.LIME_STAINED_GLASS_PANE), ChatColor.GREEN + "Preview",
        " "
    ).item();

    public static final ItemStack MENU_BACKGROUND_PREVIEW = new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.LIME_STAINED_GLASS_PANE), ChatColor.GREEN + "Preview",
        " "
    ).item();

    public static final ItemStack MENU_MARKER_ROD = new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.RED_STAINED_GLASS_PANE), ChatColor.RED + "Rod Input",
        " "
    ).item();


    public static final ItemStack MENU_MARKER_BINDER = new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.RED_STAINED_GLASS_PANE), ChatColor.RED + "Binder Input",
        " "
    ).item();

    public static final ItemStack MENU_MARKER_HEAD = new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.RED_STAINED_GLASS_PANE), ChatColor.RED + "Head/Blade Input",
        " "
    ).item();

    public static final ItemStack MENU_MARKER_LINKS = new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.RED_STAINED_GLASS_PANE), ChatColor.RED + "Links Input",
        " "
    ).item();

    public static final ItemStack MENU_MARKER_GAMBESON = new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.RED_STAINED_GLASS_PANE), ChatColor.RED + "Gambeson Input",
        " "
    ).item();


    public static final ItemStack MENU_MARKER_PLATES = new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.RED_STAINED_GLASS_PANE), ChatColor.RED + "Plates Input",
        " "
    ).item();

    public static final ItemStack MENU_PURGE = new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", VersionedPlayerHead.getItemStack(SkullTextures.BUTTON_PURGE), ThemeUtils.GUI_HEAD + "Purge Metals",
        "",
        ThemeUtils.PASSIVE + "Purge unwanted metals from the tank.",
        "",
        ThemeUtils.CLICK_INFO + "Left Click: " + ChatColor.WHITE + "Remove the TOP MOST metal",
        ThemeUtils.CLICK_INFO + "Right Click: " + ChatColor.WHITE + "Remove ALL metals"
    ).item();

    public static final ItemStack MENU_ALLOY = new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", VersionedPlayerHead.getItemStack(SkullTextures.BUTTON_ALLOY), ThemeUtils.GUI_HEAD + "Alloy Metals",
        "",
        ThemeUtils.PASSIVE + "Mixes up the metals in the tank to",
        ThemeUtils.PASSIVE + "try to create an alloy."
    ).item();

    public static final ItemStack MENU_POUR = new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", VersionedPlayerHead.getItemStack(SkullTextures.BUCKET_ORANGE), ThemeUtils.GUI_HEAD + "Pour content",
        "",
        ThemeUtils.PASSIVE + "Pours the first metal into the",
        ThemeUtils.PASSIVE + "cast. After cooling, outputs the",
        ThemeUtils.PASSIVE + "final product."
    ).item();

    public static final ItemStack MENU_CRAFT_WORKBENCH = new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.FLETCHING_TABLE), ThemeUtils.GUI_HEAD + "Craft",
        "",
        ThemeUtils.PASSIVE + "Let's get crafting!"
    ).item();

    public static final ItemStack MENU_CRAFT_TOOL_TABLE = new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.SMITHING_TABLE), ThemeUtils.GUI_HEAD + "Form Tool",
        "",
        ThemeUtils.PASSIVE + "Forms a tool from the three given",
        ThemeUtils.PASSIVE + "parts (Rod, Binding and Head/Blade)"
    ).item();

    public static final ItemStack MENU_CRAFT_ARMOUR_TABLE = new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.SMITHING_TABLE), ThemeUtils.GUI_HEAD + "Form Armour",
        "",
        ThemeUtils.PASSIVE + "Forms a piece of armour from the three given",
        ThemeUtils.PASSIVE + "parts (Plate, Gambeson and Links)"
    ).item();

    public static final ItemStack MENU_CRAFT_REPAIR = new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.CARTOGRAPHY_TABLE), ThemeUtils.GUI_HEAD + "Repair",
        "",
        ThemeUtils.PASSIVE + "Repairs the tool in the first slot",
        ThemeUtils.PASSIVE + "using the repair kit in the second."
    ).item();

    public static final ItemStack MENU_CRAFT_MOD = new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.GRINDSTONE), ThemeUtils.GUI_HEAD + "Apply Modification",
        "",
        ThemeUtils.PASSIVE + "Apply the modification to the tool."
    ).item();

    public static final ItemStack MENU_CRAFT_SWAP = new io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack("DUMMY_ID", MaterialCompat.safe(XMaterial.LOOM), ThemeUtils.GUI_HEAD + "Swap out part",
        "",
        ThemeUtils.PASSIVE + "Swap tool parts."
    ).item();

    @Nonnull
    public static ItemStack menuLavaInfo(int fillPercent, int fillAmt, int fillMax) {
        ItemStack skull;
        if (fillPercent >= 95) {
            skull = VersionedPlayerHead.getItemStack(SkullTextures.TANK_LAVA_5);
        } else if (fillPercent >= 75) {
            skull = VersionedPlayerHead.getItemStack(SkullTextures.TANK_LAVA_4);
        } else if (fillPercent >= 50) {
            skull = VersionedPlayerHead.getItemStack(SkullTextures.TANK_LAVA_3);
        } else if (fillPercent >= 25) {
            skull = VersionedPlayerHead.getItemStack(SkullTextures.TANK_LAVA_2);
        } else if (fillPercent > 0) {
            skull = VersionedPlayerHead.getItemStack(SkullTextures.TANK_LAVA_1);
        } else {
            skull = VersionedPlayerHead.getItemStack(SkullTextures.TANK_EMPTY);
        }
        List<String> meta = new ArrayList<>();
        meta.add(ThemeUtils.GUI_HEAD + "Lava Tank");
        meta.add("");
        meta.add(ThemeUtils.CLICK_INFO + "Lava: " + ChatColor.WHITE + fillAmt + " / " + fillMax);
        return CustomItemStack.create(
            skull,
            meta
        );
    }

    @Nonnull
    public static ItemStack menuMetalInfo(int fillPercent, int fillAmt, int fillMax, @Nullable Map<String, Integer> map) {
        ItemStack skull;
        if (fillPercent >= 95) {
            skull = VersionedPlayerHead.getItemStack(SkullTextures.TANK_METAL_5);
        } else if (fillPercent >= 75) {
            skull = VersionedPlayerHead.getItemStack(SkullTextures.TANK_METAL_4);
        } else if (fillPercent >= 50) {
            skull = VersionedPlayerHead.getItemStack(SkullTextures.TANK_METAL_3);
        } else if (fillPercent >= 25) {
            skull = VersionedPlayerHead.getItemStack(SkullTextures.TANK_METAL_2);
        } else if (fillPercent > 0) {
            skull = VersionedPlayerHead.getItemStack(SkullTextures.TANK_METAL_1);
        } else {
            skull = VersionedPlayerHead.getItemStack(SkullTextures.TANK_EMPTY);
        }
        List<String> meta = new ArrayList<>();
        meta.add(ThemeUtils.GUI_HEAD + "Metals Tank");
        meta.add("");
        meta.add(ThemeUtils.CLICK_INFO + "Total Metal: " + ChatColor.WHITE + fillAmt + " / " + fillMax);
        meta.add("");
        if (map != null) {
            for (Map.Entry<String, Integer> e : map.entrySet()) {
                String name =
                    TinkerMaterialManager.getById(e.getKey()).getColor() +
                        ThemeUtils.toTitleCase(e.getKey());
                String amount = e.getValue().toString();
                meta.add(ThemeUtils.CLICK_INFO + name + ": " + ChatColor.WHITE + amount + " units.");
            }
        }
        meta.add("");
        meta.add(ThemeUtils.PASSIVE + "Metals pour out from the " + ChatColor.BOLD + "top" + ThemeUtils.PASSIVE + " first");
        meta.add("");
        meta.add(ThemeUtils.CLICK_INFO + "Click to cycle metal order.");
        return CustomItemStack.create(
            skull,
            meta
        );
    }
}


