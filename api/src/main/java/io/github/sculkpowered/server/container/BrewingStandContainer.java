package io.github.sculkpowered.server.container;

import org.jetbrains.annotations.Range;

/**
 * Represents a container of a brewing stand.
 */
public interface BrewingStandContainer extends Container {

  /**
   * Sets the brew time.
   *
   * @param brewTime the brew time to set
   * @since 1.0.0
   */
  void brewTime(int brewTime);

  /**
   * Gets the brew time.
   *
   * @return the brew time
   * @since 1.0.0
   */
  int brewTime();

  /**
   * Sets the fuel time.
   *
   * @param fuelTime the fuel time to set
   * @since 1.0.0
   */
  void fuelTime(@Range(from = 0, to = 20) int fuelTime);

  /**
   * Gets the fuel time.
   *
   * @return the fuel time
   * @since 1.0.0
   */
  int fuelTime();
}
