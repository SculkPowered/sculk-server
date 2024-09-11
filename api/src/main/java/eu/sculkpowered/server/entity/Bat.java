package eu.sculkpowered.server.entity;

/**
 * Represents a bat entity.
 */
public interface Bat extends Mob {

  /**
   * @since 1.0.0
   */
  boolean hanging();

  /**
   * @since 1.0.0
   */
  void hanging(boolean hanging);
}