package de.bauhd.minecraft.server.container;

public interface StonecutterContainer extends Container {

    void setSelectedRecipe(int recipe);

    int getSelectedRecipe();
}
