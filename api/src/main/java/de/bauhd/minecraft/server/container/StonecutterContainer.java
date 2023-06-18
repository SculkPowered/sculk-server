package de.bauhd.minecraft.server.container;

/**
 * Represents a container of a stone cutter.
 */
public interface StonecutterContainer extends Container {

    void setSelectedRecipe(int recipe);

    int getSelectedRecipe();
}
