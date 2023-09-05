package de.bauhd.sculk.entity;

import de.bauhd.sculk.container.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an item frame entity.
 */
public interface ItemFrame extends Entity {

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
    int getRotation();

    /**
     * @since 1.0.0
     */
    void setRotation(int rotation);
}