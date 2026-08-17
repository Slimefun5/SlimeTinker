package io.github.sefiraat.slimetinker.i18n;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.sefiraat.slimetinker.utils.ThemeUtils;
import io.github.thebusybiscuit.slimefun5.core.services.localization.Language;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;

/**
 * Translates the fragments that make up SlimeTinker's runtime-composed tool and armour names -
 * material names, tool types and armour types - per language. Loaded from the addon's own
 * {@code languages/<lang>/tinker.yml} resources, keyed by category ("materials", "tool-types",
 * "armour-types") then by the id stored in the item's PDC.
 *
 * A missing translation falls back to {@link ThemeUtils#toTitleCase(String)} - exactly the English
 * form SlimeTinker itself produces - so an unmapped id or an English viewer sees the original name.
 */
public final class TinkerLang {

    private static final Map<String, Map<String, Map<String, String>>> BY_LANGUAGE = new HashMap<>();

    private TinkerLang() {}

    /** Loads the bundled {@code tinker.yml} for every Slimefun language. Call once after enable. */
    public static void load(@Nonnull JavaPlugin addon) {
        for (Language language : Slimefun.getLocalization().getLanguages()) {
            InputStream stream = addon.getResource("languages/" + language.getId() + "/tinker.yml");

            if (stream == null) {
                continue;
            }

            YamlConfiguration config = newTraitKeySafeYaml();

            try {
                config.load(new InputStreamReader(stream, StandardCharsets.UTF_8));
            } catch (java.io.IOException | org.bukkit.configuration.InvalidConfigurationException e) {
                Slimefun.logger().log(java.util.logging.Level.WARNING, "Failed to load tinker.yml for {0}: {1}", new Object[] { language.getId(), e.getMessage() });
                continue;
            }

            Map<String, Map<String, String>> categories = BY_LANGUAGE.computeIfAbsent(language.getId(), k -> new HashMap<>());

            for (String category : config.getKeys(false)) {
                ConfigurationSection section = config.getConfigurationSection(category);

                if (section == null) {
                    continue;
                }

                Map<String, String> entries = categories.computeIfAbsent(category, k -> new HashMap<>());

                for (String id : section.getKeys(false)) {
                    String value = section.getString(id);

                    if (value != null) {
                        entries.put(id, value);
                    }
                }
            }
        }
    }

    /**
     * A {@link YamlConfiguration} whose path separator cannot appear in any trait key.
     *
     * @implNote Trait names are used verbatim as keys and may contain '.' (e.g. "Brains, Not Brawn.").
     *           Bukkit's default '.' path separator would corrupt or blow up such keys, so we swap it
     *           for a control character that cannot occur in a name.
     */
    private static YamlConfiguration newTraitKeySafeYaml() {
        YamlConfiguration config = new YamlConfiguration();
        config.options().pathSeparator('\u001F');
        return config;
    }

    /**
     * The translated fragment for an id in the given language, or {@code null} when there is none.
     * Use where the caller has its own English baseline (e.g. part names that are not title-cased ids).
     */
    @Nullable
    public static String lookup(@Nonnull String category, @Nullable String id, @Nullable String language) {
        if (id == null || language == null) {
            return null;
        }

        Map<String, Map<String, String>> categories = BY_LANGUAGE.get(language);

        if (categories == null) {
            return null;
        }

        Map<String, String> entries = categories.get(category);
        return entries != null ? entries.get(id) : null;
    }

    /**
     * The translated fragment for an id in the given language, or its title-cased English form when
     * there is no translation (or no language).
     */
    @Nonnull
    public static String translate(@Nonnull String category, @Nullable String id, @Nullable String language) {
        if (id == null) {
            return "";
        }

        if (language != null) {
            Map<String, Map<String, String>> categories = BY_LANGUAGE.get(language);

            if (categories != null) {
                Map<String, String> entries = categories.get(category);

                if (entries != null) {
                    String value = entries.get(id);

                    if (value != null) {
                        return value;
                    }
                }
            }
        }

        return ThemeUtils.toTitleCase(id);
    }
}
