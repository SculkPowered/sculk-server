package io.github.sculkpowered.server.container;

/**
 * Represents a container of a furnace block.
 */
public interface FurnaceContainer extends Container {

  /**
   * Sets the fuel that is left in ticks to indicate the fire icon.
   *
   * @param fuelLeft the fuel left in ticks
   * @since 1.0.0
   */
  void fuelLeft(int fuelLeft);

  /**
   * Gets that is left in the container.
   *
   * @return the fuel left
   * @since 1.0.0
   */
  int fuelLeft();

  /**
   * Sets the max fuel burn time of the container.
   *
   * @param maxFuelBurnTime the max fuel burn time in ticks
   * @since 1.0.0
   */
  void maxFuelBurnTime(int maxFuelBurnTime);

  /**
   * Gets the max fuel burn time.
   *
   * @return the max fuel burn time in ticks
   * @since 1.0.0
   */
  int maxFuelBurnTime();

  /**
   * @since 1.0.0
   */
  void progressArrow(int progressArrow);

  /**
   * @since 1.0.0
   */
  int progressArrow();

  /**
   * @since 1.0.0
   */
  void maximumProgress(int maximumProgress);

  /**
   * @since 1.0.0
   */
  int maximumProgress();
}
