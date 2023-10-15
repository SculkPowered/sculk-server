package de.bauhd.sculk.entity;

/**
 * Represents an entity that can age.
 */
public interface Ageable {

  /**
   * Gets if the entity is a baby or not.
   *
   * @return true if it is a baby
   * @since 1.0.0
   */
  boolean isBaby();

  /**
   * Sets the entity to a baby.
   */
  void setBaby();

  /**
   * Gets if the entity is an adult or not.
   *
   * @return true if it is an adult
   * @since 1.0.0
   */
  boolean isAdult();

  /**
   * Sets the entity to an adult.
   *
   * @since 1.0.0
   */
  void setAdult();
}
