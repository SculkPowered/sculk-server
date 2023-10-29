package io.github.sculkpowered.server.entity;

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
  void saddle(boolean saddle);
}