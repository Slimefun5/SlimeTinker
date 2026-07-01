package io.github.sefiraat.slimetinker.utils;

import java.lang.reflect.Method;

import javax.annotation.Nonnull;

import org.bukkit.entity.LivingEntity;

/**
 * Version-safe access to an entity's maximum health.
 *
 * Modern servers expose this via {@code getAttribute(Attribute.GENERIC_MAX_HEALTH)}
 * (the {@code org.bukkit.attribute} package arrived in 1.9). On 1.8 that package is
 * absent, so we fall back to the long-standing deprecated {@code getMaxHealth()}.
 * All access is reflective to keep the {@code Attribute} class off the load path.
 */
public final class AttributeCompat {

    private static final Object MAX_HEALTH = resolveMaxHealthAttribute();
    private static final Method GET_ATTRIBUTE = resolveGetAttribute();
    private static final Method GET_VALUE = resolveGetValue();

    private AttributeCompat() {
        throw new UnsupportedOperationException("Utility Class");
    }

    public static double getMaxHealth(@Nonnull LivingEntity entity) {
        if (MAX_HEALTH != null && GET_ATTRIBUTE != null && GET_VALUE != null) {
            try {
                Object instance = GET_ATTRIBUTE.invoke(entity, MAX_HEALTH);
                if (instance != null) {
                    return ((Number) GET_VALUE.invoke(instance)).doubleValue();
                }
            } catch (ReflectiveOperationException | ClassCastException e) {
                // Fall through to the legacy accessor.
            }
        }
        return entity.getMaxHealth();
    }

    @SuppressWarnings("unchecked")
    private static Object resolveMaxHealthAttribute() {
        try {
            Class<?> attribute = Class.forName("org.bukkit.attribute.Attribute");
            return Enum.valueOf((Class<? extends Enum>) attribute, "GENERIC_MAX_HEALTH");
        } catch (ReflectiveOperationException | IllegalArgumentException e) {
            return null;
        }
    }

    private static Method resolveGetAttribute() {
        try {
            Class<?> attribute = Class.forName("org.bukkit.attribute.Attribute");
            return LivingEntity.class.getMethod("getAttribute", attribute);
        } catch (ReflectiveOperationException e) {
            return null;
        }
    }

    private static Method resolveGetValue() {
        try {
            return Class.forName("org.bukkit.attribute.AttributeInstance").getMethod("getValue");
        } catch (ReflectiveOperationException e) {
            return null;
        }
    }
}
