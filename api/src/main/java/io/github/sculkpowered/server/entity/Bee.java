package io.github.sculkpowered.server.entity;

/**
 * Represents a bee entity.
 */
public interface Bee extends Animal {

  /**
   * @since 1.0.0
   */
  boolean angry();

  /**
   * @since 1.0.0
   */
  void angry(boolean angry);

  /**
   * @since 1.0.0
   */
  boolean stung();

  /**
   * @since 1.0.0
   */
  void stung(boolean stung);

  /**
   * @since 1.0.0
   */
  boolean nectar();

  /**
   * @since 1.0.0
   */
  void nectar(boolean nectar);
}