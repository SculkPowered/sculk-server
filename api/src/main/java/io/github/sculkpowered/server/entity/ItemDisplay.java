package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.container.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an item display entity.
 */
public interface ItemDisplay extends Display {

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
  @NotNull DisplayType displayType();

  /**
   * @since 1.0.0
   */
  void displayType(@NotNull DisplayType displayType);

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