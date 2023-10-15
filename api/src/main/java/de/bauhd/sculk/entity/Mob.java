package de.bauhd.sculk.entity;

/**
 * Represents a mob entity.
 */
public interface Mob extends LivingEntity {

  /**
   * Gets whether the mob is aggressive.
   *
   * @return whether the mob is aggressive
   */
  boolean isAggressive();

}
