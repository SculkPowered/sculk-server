package de.bauhd.sculk.container;

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
    void setFuelLeft(int fuelLeft);

    /**
     * Gets that is left in the container.
     *
     * @return the fuel left
     * @since 1.0.0
     */
    int getFuelLeft();

    /**
     * Sets the max fuel burn time of the container.
     *
     * @param maxFuelBurnTime the max fuel burn time in ticks
     * @since 1.0.0
     */
    void setMaxFuelBurnTime(int maxFuelBurnTime);

    /**
     * Gets the max fuel burn time.
     *
     * @return the max fuel burn time in ticks
     * @since 1.0.0
     */
    int getMaxFuelBurnTime();

    /**
     * @since 1.0.0
     */
    void setProgressArrow(int progressArrow);

    /**
     * @since 1.0.0
     */
    int getProgressArrow();

    /**
     * @since 1.0.0
     */
    void setMaximumProgress(int maximumProgress);

    /**
     * @since 1.0.0
     */
    int getMaximumProgress();
}
