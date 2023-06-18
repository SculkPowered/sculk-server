package de.bauhd.minecraft.server.container;

/**
 * Represents a container of a furnace block.
 */
public interface FurnaceContainer extends Container {

    void setFuelLeft(int fuelLeft);

    int getFuelLeft();

    void setMaxFuelBurnTime(int maxFuelBurnTime);

    int getMaxFuelBurnTime();

    void setProgressArrow(int progressArrow);

    int getProgressArrow();

    void setMaximumProgress(int maximumProgress);

    int getMaximumProgress();
}
