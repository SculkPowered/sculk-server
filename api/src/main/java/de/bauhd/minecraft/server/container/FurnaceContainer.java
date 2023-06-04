package de.bauhd.minecraft.server.container;

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
