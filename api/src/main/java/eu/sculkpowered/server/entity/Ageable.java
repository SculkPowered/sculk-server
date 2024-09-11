package eu.sculkpowered.server.entity;

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
  boolean baby();

  /**
   * Gets if the entity is an adult or not.
   *
   * @return true if it is an adult
   * @since 1.0.0
   */
  boolean adult();

  /**
   * Sets the entity to a baby or not.
   *
   * @param baby whether the entity should be a baby
   */
  void setBaby(boolean baby);
}
