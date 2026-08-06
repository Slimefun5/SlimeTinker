package io.github.sefiraat.slimetinker;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.github.thebusybiscuit.slimefun5.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.core.guide.wiki.WikiText;
import io.github.thebusybiscuit.slimefun5.core.guide.wiki.WikiTopic;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;

import io.github.sefiraat.slimetinker.i18n.TinkerItemResolver;
import io.github.sefiraat.slimetinker.i18n.TinkerLang;
import io.github.sefiraat.slimetinker.itemgroups.ItemGroups;
import io.github.sefiraat.slimetinker.items.Casts;
import io.github.sefiraat.slimetinker.items.Dies;
import io.github.sefiraat.slimetinker.items.Guide;
import io.github.sefiraat.slimetinker.items.Materials;
import io.github.sefiraat.slimetinker.items.Mods;
import io.github.sefiraat.slimetinker.items.Parts;
import io.github.sefiraat.slimetinker.items.Workstations;
import io.github.sefiraat.slimetinker.items.tinkermaterials.TinkerMaterialManager;
import io.github.sefiraat.slimetinker.items.workstations.workbench.Workbench;
import io.github.sefiraat.slimetinker.listeners.ListenerManager;
import io.github.sefiraat.slimetinker.managers.DispatchManager;
import io.github.sefiraat.slimetinker.managers.MemoryManager;
import io.github.sefiraat.slimetinker.managers.TraitManager;
import io.github.sefiraat.slimetinker.runnables.RunnableManager;
import io.github.thebusybiscuit.slimefun5.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun5.libraries.dough.updater.BlobBuildUpdater;

import org.bukkit.plugin.java.JavaPlugin;
import dev.walshy.sfmetrics.MetricsModule;

public class SlimeTinker extends JavaPlugin implements SlimefunAddon {

    public static final int RUNNABLE_TICK_RATE = 40;

    private static SlimeTinker instance;

    private final String username;
    private final String repo;
    private final String branch;

    private RunnableManager runnableManager;
    private ListenerManager listenerManager;
    private TinkerMaterialManager tinkerMaterialManager;
    private DispatchManager dispatchManager;
    private Workbench workbench;
    private TraitManager traitManager;
    private MemoryManager memoryManager;

    public SlimeTinker() {
        this.username = "Slimefun5";
        this.repo = "SlimeTinker";
        this.branch = "master";
    }

    @Override
    public void onEnable() {
        MetricsModule.setup(this, 31392);


        
        instance = this;

        // Startup banner intentionally omitted: Slimefun core logs every installed addon uniformly.

        ItemGroups.set(this);
        Materials.set(this);
        Dies.set(this);
        Casts.set(this);
        Parts.set(this);
        Guide.set(this);
        Mods.set(this);
        Workstations.set(this);

        traitManager = new TraitManager();
        tinkerMaterialManager = new TinkerMaterialManager();

        // Classify all registered items into shared guide categories. Runs after TinkerMaterialManager so
        // the material-derived groups (molten metals, alloys, traits, part dictionary) are populated first -
        // categorise() iterates each group's items, so an empty group at this point types nothing.
        ItemGroups.categorise();

        runnableManager = new RunnableManager();
        dispatchManager = new DispatchManager();
        memoryManager = new MemoryManager();

        this.listenerManager = new ListenerManager(this, this.getServer().getPluginManager());

        if (getConfig().getBoolean("auto-update") && getDescription().getVersion().startsWith("Dev")) {
            new BlobBuildUpdater(this, getFile(), "SlimeTinker", "Dev").start();
        }

        Slimefun.getItemTranslationService().registerTranslations(this);
        TinkerLang.load(this);
        // Assembled tools/armour/parts are named+lored at runtime from their PDC, so they route through
        // core's per-viewer packet layer via an item-aware resolver (replaces the old re-skin listener).
        Slimefun.getItemTranslationService().registerResolver(new TinkerItemResolver());
        registerWiki();
    }

    private void registerWiki() {
        WikiText wiki = Slimefun.getWikiText();

        // Bucket this addon's items by their ItemGroup, preserving registration order.
        Map<ItemGroup, List<String>> byGroup = new LinkedHashMap<>();
        for (SlimefunItem item : Slimefun.getRegistry().getEnabledSlimefunItems()) {
            try {
                if (item.getAddon() != this) {
                    continue;
                }
                byGroup.computeIfAbsent(item.getItemGroup(), key -> new ArrayList<>()).add(item.getId());
            } catch (Exception | LinkageError ignored) {
                // Skip items that fail to resolve their addon/group on exotic versions.
            }
        }

        for (Map.Entry<ItemGroup, List<String>> entry : byGroup.entrySet()) {
            ItemGroup group = entry.getKey();
            String groupKey = group.getKey().getKey();
            String topicId = "addon_slimetinker_" + groupKey;

            wiki.registerTopic(new WikiTopic(
                topicId,
                getWikiTopicName(groupKey),
                getWikiTopicIcon(groupKey),
                getWikiTopicTagline(groupKey)
            ));
            wiki.setMechanic(topicId, getWikiMechanic(groupKey));
            wiki.setTopicItems(topicId, entry.getValue());
        }

        registerItemPages(wiki);
    }

    @Nonnull
    private String getWikiTopicName(@Nonnull String groupKey) {
        switch (groupKey) {
            case "slime-tinker-workstations": return "SlimeTinker: Workstations";
            case "slime-tinker-materials": return "SlimeTinker: Materials";
            case "slime-tinker-casts": return "SlimeTinker: Casts & Dies";
            case "slime-tinker-parts": return "SlimeTinker: Tool & Armour Parts";
            case "slime-tinker-tools": return "SlimeTinker: Tools";
            case "slime-tinker-armour": return "SlimeTinker: Armour";
            case "slime-tinker-modifications": return "SlimeTinker: Modifications";
            default: return "SlimeTinker: Miscellaneous";
        }
    }

    @Nonnull
    private XMaterial getWikiTopicIcon(@Nonnull String groupKey) {
        switch (groupKey) {
            case "slime-tinker-workstations": return XMaterial.SMITHING_TABLE;
            case "slime-tinker-materials": return XMaterial.RAW_IRON;
            case "slime-tinker-casts": return XMaterial.IRON_INGOT;
            case "slime-tinker-parts": return XMaterial.STICK;
            case "slime-tinker-tools": return XMaterial.DIAMOND_PICKAXE;
            case "slime-tinker-armour": return XMaterial.DIAMOND_CHESTPLATE;
            case "slime-tinker-modifications": return XMaterial.REDSTONE;
            default: return XMaterial.ANVIL;
        }
    }

    @Nonnull
    private String getWikiTopicTagline(@Nonnull String groupKey) {
        switch (groupKey) {
            case "slime-tinker-workstations": return "&7The machines that drive the forge";
            case "slime-tinker-materials": return "&7Ores, bricks, molten metals & alloys";
            case "slime-tinker-casts": return "&7Carve dies, cast metal into parts";
            case "slime-tinker-parts": return "&7The building blocks of every tool";
            case "slime-tinker-tools": return "&7Modular tools that level up";
            case "slime-tinker-armour": return "&7Modular armour that levels up";
            case "slime-tinker-modifications": return "&7Bolt extra powers onto your gear";
            default: return "&7Modular, upgradeable tools & armour";
        }
    }

    @Nonnull
    private List<String> getWikiMechanic(@Nonnull String groupKey) {
        switch (groupKey) {
            case "slime-tinker-workstations":
                return Arrays.asList(
                    "&7SlimeTinker is built around a chain of",
                    "&7workstations, each handling one step of",
                    "&7the forging process.",
                    "",
                    "&71. The &eTinker's Smeltery &7melts metals into",
                    "&7liquid and alloys them, fuelled by lava.",
                    "&72. The &eWorkbench &7crafts dies, casts and blocks.",
                    "&73. The &eTool &7and &eArmour Tables &7assemble parts.",
                    "&74. The &eRepair Bench &7restores durability.",
                    "&75. The &eSwapping Station &7replaces worn parts.",
                    "&76. The &eModification Station &7applies modifiers.",
                    "",
                    "&7Click a station below for its recipe.");
            case "slime-tinker-materials":
                return Arrays.asList(
                    "&7Everything you forge starts as raw metal.",
                    "",
                    "&7Mine and crush ores into &edusts&7, then melt",
                    "&7them in the Smeltery into &emolten metal&7.",
                    "&7Two molten metals can be combined into an",
                    "&7&ealloy &7such as Bronze, Steel or Damascus.",
                    "",
                    "&7Seared Bricks build the Smeltery itself,",
                    "&7while cast metal blocks craft the tables.",
                    "&7Each metal has its own &etraits &7that pass",
                    "&7on to any part poured from it.",
                    "",
                    "&7Click a material below for its recipe.");
            case "slime-tinker-casts":
                return Arrays.asList(
                    "&7A &ecast &7defines the shape a molten metal",
                    "&7takes when poured at the Smeltery spout.",
                    "",
                    "&7First carve a &edie &7from a metal block at",
                    "&7the Workbench. A die is reusable forever.",
                    "&7Pour molten metal over the die to create",
                    "&7a single-use &ecast &7of that part.",
                    "",
                    "&7Pour again over the cast to mass-produce",
                    "&7parts, consuming only the metal.",
                    "&7Casts and dies exist for every head, plate,",
                    "&7rod, binding, gem, ingot and repair kit.",
                    "",
                    "&7Click a cast or die below for its recipe.");
            case "slime-tinker-parts":
                return Arrays.asList(
                    "&7Every Tinker's tool and armour piece is",
                    "&7assembled from three &eparts&7.",
                    "",
                    "&7Parts are poured from molten metal using a",
                    "&7cast. The metal you choose decides which",
                    "&7&etraits &7the finished part contributes.",
                    "",
                    "&7Tools take a &eHead&7, &eTool Rod &7and &eBinding&7.",
                    "&7Armour takes a plate, &eGambeson &7and &eMail Links&7.",
                    "&7The &eRepair Kit &7refills durability at the",
                    "&7Repair Bench.",
                    "",
                    "&7Click a part below for its recipe.");
            case "slime-tinker-tools":
                return Arrays.asList(
                    "&7Tinker's tools are forged from three parts",
                    "&7at the &eTool Table&7: a Head, a Tool Rod and",
                    "&7a Binding.",
                    "",
                    "&7Each part's metal grants &etraits&7, so two",
                    "&7tools of the same type can behave wildly",
                    "&7differently.",
                    "",
                    "&7Tools start as &7Stone and gain EXP as you",
                    "&7use them, promoting &eStone > Gold > Iron >",
                    "&7Diamond > Netherite &7and unlocking modifier",
                    "&7slots along the way.",
                    "",
                    "&7Click a tool below for its recipe.");
            case "slime-tinker-armour":
                return Arrays.asList(
                    "&7Tinker's armour is forged from three parts",
                    "&7at the &eArmour Table&7: a plate, a Gambeson",
                    "&7and Mail Links.",
                    "",
                    "&7As with tools, the metal of each part adds",
                    "&7&etraits&7, and the piece levels up with use.",
                    "&7Armour promotes &eLeather > Chain > Iron >",
                    "&7Diamond > Netherite&7.",
                    "",
                    "&7When a piece breaks it is unequipped rather",
                    "&7than destroyed; if your inventory is full it",
                    "&7drops on the floor instead.",
                    "",
                    "&7Click a piece below for its recipe.");
            case "slime-tinker-modifications":
                return Arrays.asList(
                    "&7Modifications bolt extra powers onto a",
                    "&7finished tool or armour piece.",
                    "",
                    "&7Apply them at the &eModification Station&7.",
                    "&7Each tool has a limited number of modifier",
                    "&7slots, unlocked as the tool levels up.",
                    "",
                    "&7Most modifiers can be stacked to raise their",
                    "&7level, strengthening the effect each time.",
                    "&7Examples: &eRedstone &7grants Haste, &ePlate",
                    "&7&7saves durability, &eEmerald &7boosts EXP gain.",
                    "",
                    "&7Click a modification below for its recipe.");
            default:
                return Arrays.asList(
                    "&7Supporting and utility items used across",
                    "&7the SlimeTinker forging process.",
                    "",
                    "&7Click an item below for its recipe.");
        }
    }

    private void registerItemPages(@Nonnull WikiText wiki) {
        // Workstations
        wiki.set("TINKERS_SMELTERY_CORE", Arrays.asList(
            "&7The heart of SlimeTinker. Build the seared",
            "&7brick multiblock around this controller.",
            "&7Fuelled by &elava&7, it melts metals into liquid,",
            "&7alloys them, and pours casts via the spout."));
        wiki.set("TINKERS_WORKBENCH", Arrays.asList(
            "&7Your first SlimeTinker machine and an",
            "&7Enhanced Crafting Table in one.",
            "&7Used to craft dies, casts, blocks and the",
            "&7other workstations."));
        wiki.set("TINKERS_TABLE", Arrays.asList(
            "&7Combines a Head, Tool Rod and Binding into",
            "&7a finished Tinker's tool.",
            "&7The metals of the parts decide the tool's",
            "&7traits."));
        wiki.set("TINKERS_ARMOUR_TABLE", Arrays.asList(
            "&7Combines a plate, Gambeson and Mail Links",
            "&7into a finished Tinker's armour piece."));
        wiki.set("TINKERS_REPAIR_BENCH", Arrays.asList(
            "&7Restores durability to a worn tool or armour",
            "&7piece using a Repair Kit poured from metal."));
        wiki.set("TINKERS_SWAPPING_STATION", Arrays.asList(
            "&7Swaps an installed part of a tool for a new",
            "&7one, letting you re-roll its traits without",
            "&7rebuilding from scratch."));
        wiki.set("TINKERS_MOD_STATION", Arrays.asList(
            "&7Applies modifications to a finished tool or",
            "&7armour piece, consuming an open modifier slot.",
            "&7Re-applying the same modifier raises its level."));

        // Parts & repair
        wiki.set("PART_TOOL_ROD_DUMMY", Arrays.asList(
            "&7The handle of every Tinker's tool.",
            "&7Poured from molten metal using a Tool Rod cast;",
            "&7its metal contributes traits to the tool."));
        wiki.set("PART_BINDING_DUMMY", Arrays.asList(
            "&7Binds the head to the rod on a Tinker's tool.",
            "&7Its metal adds a third set of traits."));
        wiki.set("PART_GAMBESON_DUMMY", Arrays.asList(
            "&7The padded layer of a Tinker's armour piece.",
            "&7One of the three parts every piece needs."));
        wiki.set("PART_MAIL_LINKS_DUMMY", Arrays.asList(
            "&7Chain links that hold an armour piece together.",
            "&7Its metal adds traits to the finished armour."));
        wiki.set("PART_REPAIR_KIT_DUMMY", Arrays.asList(
            "&7Poured from molten metal at the Smeltery.",
            "&7Used at the Repair Bench to restore the",
            "&7durability of a matching tool or armour piece."));

        // Tools & armour info
        wiki.set("TOOL_INFO", Arrays.asList(
            "&7Tinker's tools start as Stone. As they gain",
            "&7EXP they promote &eGold > Iron > Diamond >",
            "&7Netherite&7, unlocking modifier slots as they go."));
        wiki.set("ARMOUR_INFO", Arrays.asList(
            "&7Tinker's armour starts as Leather and promotes",
            "&7&eChain > Iron > Diamond > Netherite &7as it levels."));

        // Modifications
        wiki.set("MOD_REDSTONE_DUMMY", Arrays.asList(
            "&7Grants the holder &eHaste &7matching the",
            "&7modification's level."));
        wiki.set("MOD_LAPIS_DUMMY", Arrays.asList(
            "&7Drops bonus items from blocks and kills,",
            "&7calculated after Fortune/Looting. +10% per level."));
        wiki.set("MOD_QUARTZ_DUMMY", Arrays.asList(
            "&7Deals bonus damage on top of Sharpness and",
            "&7similar enchants. +20% per level."));
        wiki.set("MOD_DIAMOND_DUMMY", Arrays.asList(
            "&7Reflects a share of damage taken back at the",
            "&7attacker. 10% per level."));
        wiki.set("MOD_EMERALD_DUMMY", Arrays.asList(
            "&7Grants bonus tool EXP per action: +1 for tools,",
            "&7+10% for weapons."));
        wiki.set("MOD_PLATE_DUMMY", Arrays.asList(
            "&7Chance to skip durability damage. 10% per level;",
            "&7at Level 10 the tool becomes unbreakable."));
    }

    @Override
    public void onDisable() {
        saveConfig();
        instance = null;
    }


    public RunnableManager getRunnableManager() {
        return runnableManager;
    }

    public ListenerManager getListenerManager() {
        return listenerManager;
    }

    public TinkerMaterialManager getCmManager() {
        return tinkerMaterialManager;
    }

    public DispatchManager getDispatchManager() {
        return dispatchManager;
    }

    public Workbench getWorkbench() {
        return workbench;
    }

    public void setWorkbench(Workbench workbench) {
        this.workbench = workbench;
    }

    public TraitManager getTraitManager() {
        return traitManager;
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nullable
    @Override
    public String getBugTrackerURL() {
        return MessageFormat.format("https://github.com/{0}/{1}/issues/", this.username, this.repo);
    }

    public static SlimeTinker getInstance() {
        return instance;
    }
}

