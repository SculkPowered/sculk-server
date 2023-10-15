package de.bauhd.sculk.entity;

/**
 * Represents a pig entity.
 */
public interface Pig extends Animal {

  /**
   * @since 1.0.0
   */
  boolean hasSaddle();

  /**
   * @since 1.0.0
   */
  void setSaddle(boolean saddle);
}