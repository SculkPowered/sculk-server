package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.container.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an item frame entity.
 */
public interface ItemFrame extends Entity {

  /**
   * @since 1.0.0
   */
  @NotNull ItemStack item();

  /**
   * @since 1.0.0
   */
  void item(@NotNull ItemStack item);

  /**
   * @since 1.0.0
   */
  int rotation();

  /**
   * @since 1.0.0
   */
  void rotation(int rotation);
}