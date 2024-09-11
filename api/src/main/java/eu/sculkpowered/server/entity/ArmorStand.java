package eu.sculkpowered.server.entity;

/**
 * Represents an armor stand.
 */
public interface ArmorStand extends LivingEntity {

  /**
   * Gets whether this armor stand is small or not.
   *
   * @return whether it's small or not
   * @since 1.0.0
   */
  boolean small();

  /**
   * Sets the armor stand small.
   *
   * @since 1.0.0
   */
  void setSmall();

  /**
   * Gets whether this armor stand is big or not.
   *
   * @return whether it's big or not
   * @since 1.0.0
   */
  boolean big();

  /**
   * Sets the armor stand big.
   *
   * @since 1.0.0
   */
  void setBig();

  /**
   * Gets whether the armor stand has arms or not.
   *
   * @return whether this armor stand has arms
   * @since 1.0.0
   */
  boolean hasArms();

  /**
   * Sets whether the armor stand has arms.
   *
   * @param arms whether this armor stand has arms
   * @since 1.0.0
   */
  void arms(boolean arms);

  /**
   * Gets whether the armor stand has a baseplate or not.
   *
   * @return whether this armor stand has a baseplate
   * @since 1.0.0
   */
  boolean hasBasePlate();

  /**
   * Sets whether the armor stand has a baseplate.
   *
   * @param basePlate whether this armor stand has a baseplate
   * @since 1.0.0
   */
  void basePlate(boolean basePlate);

  /**
   * Gets whether the armor stand is a marker or not.
   *
   * @return whether this armor stand is a marker
   * @since 1.0.0
   */
  boolean marker();

  /**
   * Sets whether the armor stand is a marker.
   *
   * @param marker whether this armor stand is a marker
   * @since 1.0.0
   */
  void marker(boolean marker);
}
