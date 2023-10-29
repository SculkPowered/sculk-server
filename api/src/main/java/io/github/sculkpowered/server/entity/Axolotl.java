package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

/**
 * Represents an axolotl entity.
 */
public interface Axolotl extends Animal {

  /**
   * @since 1.0.0
   */
  @NotNull Axolotl.Variant variant();

  /**
   * @since 1.0.0
   */
  void variant(@NotNull Variant variant);

  /**
   * @since 1.0.0
   */
  boolean playingDead();

  /**
   * @since 1.0.0
   */
  void playingDead(boolean playingDead);

  enum Variant {
    LUCY,
    WILD,
    GOLD,
    CYAN,
    BLUE
  }
}