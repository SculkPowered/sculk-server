package de.bauhd.sculk.entity;

/**
 * Represents a piglin entity.
 */
public interface Piglin extends Mob, Ageable {

  /**
   * @since 1.0.0
   */
  boolean isCharging();

  /**
   * @since 1.0.0
   */
  void setCharging(boolean charging);

  /**
   * @since 1.0.0
   */
  boolean isDancing();

  /**
   * @since 1.0.0
   */
  void setDancing(boolean dancing);
}