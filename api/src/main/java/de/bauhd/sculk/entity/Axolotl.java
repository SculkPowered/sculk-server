package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

/**
 * Represents an axolotl entity.
 */
public interface Axolotl extends Animal {

  /**
   * @since 1.0.0
   */
  @NotNull Axolotl.Variant getVariant();

  /**
   * @since 1.0.0
   */
  void setVariant(@NotNull Variant variant);

  /**
   * @since 1.0.0
   */
  boolean isPlayingDead();

  /**
   * @since 1.0.0
   */
  void setPlayingDead(boolean playingDead);

  enum Variant {
    LUCY,
    WILD,
    GOLD,
    CYAN,
    BLUE
  }
}