package de.bauhd.sculk.entity;

/**
 * Represents a pillager entity.
 */
public interface Pillager extends Raider {

  /**
   * @since 1.0.0
   */
  boolean isCharging();

  /**
   * @since 1.0.0
   */
  void setCharging(boolean charging);
}