package de.bauhd.sculk.entity;

import de.bauhd.sculk.container.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an item display entity.
 */
public interface ItemDisplay extends Display {

    /**
     * @since 1.0.0
     */
    @NotNull ItemStack getItem();

    /**
     * @since 1.0.0
     */
    void setItem(@NotNull ItemStack item);

    /**
     * @since 1.0.0
     */
    @NotNull DisplayType getDisplayType();

    /**
     * @since 1.0.0
     */
    void setDisplayType(@NotNull DisplayType displayType);

    enum DisplayType {
        NONE,
        THIRD_PERSON_LEFT_HAND,
        THIRD_PERSON_RIGHT_HAND,
        FIRST_PERSON_LEFT_HAND,
        FIRST_PERSON_RIGHT_HAND,
        HEAD,
        GUI,
        GROUND,
        FIXED
    }
}