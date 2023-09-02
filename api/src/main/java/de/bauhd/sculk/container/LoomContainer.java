package de.bauhd.sculk.container;

/**
 * Represents a container of a loom block.
 */
public interface LoomContainer extends Container {

    void setSelectedPattern(int pattern);

    int getSelectedPattern();
}
