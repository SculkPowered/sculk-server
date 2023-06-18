package de.bauhd.minecraft.server.container;

/**
 * Represents a container of a furnace block.
 */
public interface FurnaceContainer extends Container {

    /**
     * Sets the fuel that is left in ticks to indicate the fire icon.
     * @param fuelLeft the fuel left in ticks
     */
    void setFuelLeft(int fuelLeft);

    /**
     * Gets that is left in the container.
     * @return the fuel left
     */
    int getFuelLeft();

    /**
     * Sets the max fuel burn time of the container.
     * @param maxFuelBurnTime the max fuel burn time in ticks
     */
    void setMaxFuelBurnTime(int maxFuelBurnTime);

    /**
     * Gets the max fuel burn time.
     * @return the max fuel burn time in ticks
     */
    int getMaxFuelBurnTime();

    void setProgressArrow(int progressArrow);

    int getProgressArrow();

    void setMaximumProgress(int maximumProgress);

    int getMaximumProgress();
}
