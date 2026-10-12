package io.github.sefiraat.slimetinker.i18n;

import java.util.function.UnaryOperator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;

import io.github.sefiraat.slimetinker.SlimeTinker;
import io.github.sefiraat.slimetinker.utils.ThemeUtils;

/**
 * Resolves this addon's chat text for one viewer, from {@code languages/<lang>/messages.yml}.
 *
 * @implNote Separate from {@link TinkerLang}, which translates the name FRAGMENTS a tool's name is
 *           composed from and is keyed by PDC id; this is ordinary chat text keyed by message. The
 *           {@link ThemeUtils} colour deliberately stays at the call site rather than moving into
 *           the translated value, so a translator never edits a colour code - and several of those
 *           colours are hex, which has no {@code &} form on the legacy versions this jar supports.
 */
public final class Messages {

    private Messages() {}

    @Nonnull
    public static String get(@Nullable Player player, @Nonnull String key, @Nonnull String english) {
        return Slimefun.getAddonMessageService().getMessage(player, SlimeTinker.getInstance(), key, english);
    }

    public static void send(@Nonnull CommandSender recipient, @Nonnull ChatColor colour, @Nonnull String key, @Nonnull String english) {
        send(recipient, colour, key, english, UnaryOperator.identity());
    }

    public static void send(@Nonnull CommandSender recipient, @Nonnull ChatColor colour, @Nonnull String key, @Nonnull String english, @Nonnull UnaryOperator<String> placeholders) {
        Slimefun.getAddonMessageService().sendMessage(recipient, SlimeTinker.getInstance(), key, english,
                line -> colour + placeholders.apply(line));
    }
}
