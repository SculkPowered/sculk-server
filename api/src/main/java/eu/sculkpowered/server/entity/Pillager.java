package eu.sculkpowered.server.entity;

/**
 * Represents a pillager entity.
 */
public interface Pillager extends Raider {

  /**
   * @since 1.0.0
   */
  boolean charging();

  /**
   * @since 1.0.0
   */
  void charging(boolean charging);
}