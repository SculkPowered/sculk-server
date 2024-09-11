package eu.sculkpowered.server.entity;

/**
 * Represents a creeper entity.
 */
public interface Creeper extends Mob {

  /**
   * @since 1.0.0
   */
  boolean isIdling();

  /**
   * @since 1.0.0
   */
  void idling();

  /**
   * @since 1.0.0
   */
  boolean isFused();

  /**
   * @since 1.0.0
   */
  void fusing();

  /**
   * @since 1.0.0
   */
  boolean isCharged();

  /**
   * @since 1.0.0
   */
  void charged(boolean charged);

  /**
   * @since 1.0.0
   */
  boolean isIgnited();

  /**
   * @since 1.0.0
   */
  void ignited(boolean ignited);
}