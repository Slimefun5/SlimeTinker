package io.github.sefiraat.slimetinker.utils;

import java.lang.reflect.Method;

import javax.annotation.Nonnull;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;

/**
 * Version-safe delivery of a BungeeCord chat {@link BaseComponent}.
 *
 * Modern servers expose {@code Player.spigot().sendMessage(BaseComponent)}; that
 * path is taken reflectively when available. On servers lacking it the supplied
 * legacy text is sent through {@link Player#sendMessage(String)} so the message
 * still reaches the player.
 */
public final class ChatCompat {

    private static final Method SPIGOT = resolve(Player.class, "spigot");
    private static final Method SEND_COMPONENT = resolveSendComponent();

    private ChatCompat() {
        throw new UnsupportedOperationException("Utility Class");
    }

    public static void sendMessage(@Nonnull Player player, @Nonnull BaseComponent component, @Nonnull String legacyFallback) {
        if (SPIGOT != null && SEND_COMPONENT != null) {
            try {
                Object spigot = SPIGOT.invoke(player);
                SEND_COMPONENT.invoke(spigot, component);
                return;
            } catch (ReflectiveOperationException e) {
                // Fall through to the plain-text fallback.
            }
        }
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', legacyFallback));
    }

    private static Method resolve(Class<?> owner, String name) {
        try {
            return owner.getMethod(name);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private static Method resolveSendComponent() {
        if (SPIGOT == null) {
            return null;
        }
        try {
            return SPIGOT.getReturnType().getMethod("sendMessage", BaseComponent.class);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
