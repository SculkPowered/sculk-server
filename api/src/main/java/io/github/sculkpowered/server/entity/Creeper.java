package io.github.sculkpowered.server.entity;

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
  void setIdling();

  /**
   * @since 1.0.0
   */
  boolean isFused();

  /**
   * @since 1.0.0
   */
  void setFusing();

  /**
   * @since 1.0.0
   */
  boolean isCharged();

  /**
   * @since 1.0.0
   */
  void setCharged(boolean charged);

  /**
   * @since 1.0.0
   */
  boolean isIgnited();

  /**
   * @since 1.0.0
   */
  void setIgnited(boolean ignited);
}