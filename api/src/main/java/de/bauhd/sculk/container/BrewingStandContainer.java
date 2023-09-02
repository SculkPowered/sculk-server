package de.bauhd.sculk.container;

import org.jetbrains.annotations.Range;

/**
 * Represents a container of a brewing stand.
 */
public interface BrewingStandContainer extends Container {

    /**
     * Sets the brew time.
     * @param brewTime the brew time to set
     */
    void setBrewTime(int brewTime);

    /**
     * Gets the brew time.
     * @return the brew time
     */
    int getBrewTime();

    /**
     * Sets the fuel time.
     * @param fuelTime the fuel time to set
     */
    void setFuelTime(@Range(from = 0, to = 20) int fuelTime);

    /**
     * Gets the fuel time.
     * @return the fuel time
     */
    int getFuelTime();
}
