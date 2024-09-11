package eu.sculkpowered.server.entity;

/**
 * Represents a goat entity.
 */
public interface Goat extends Animal {

  /**
   * @since 1.0.0
   */
  boolean isScreaming();

  /**
   * @since 1.0.0
   */
  void isScreaming(boolean screaming);

  /**
   * @since 1.0.0
   */
  boolean hasLeftHorn();

  /**
   * @since 1.0.0
   */
  void hasLeftHorn(boolean leftHorn);

  /**
   * @since 1.0.0
   */
  boolean hasRightHorn();

  /**
   * @since 1.0.0
   */
  void rightHorn(boolean rightHorn);
}