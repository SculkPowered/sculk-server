package de.bauhd.minecraft.server.container;

/**
 * Represents a container of an anvil block.
 */
public interface AnvilContainer extends Container {

    void setRepairCost(int repairCost);

    int getRepairCost();
}
