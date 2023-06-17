package de.bauhd.minecraft.server.container;

import org.jetbrains.annotations.Range;

public interface BrewingStandContainer extends Container {

    void setBrewTime(int brewTime);

    int getBrewTime();

    void setFuelTime(@Range(from = 0, to = 20) int fuelTime);

    int getFuelTime();
}