package de.bauhd.sculk.container;

/**
 * Represents a container of an anvil block.
 */
public interface AnvilContainer extends Container {

  /**
   * Sets the repair cost to display.
   *
   * @param repairCost the repair cost
   * @since 1.0.0
   */
  void setRepairCost(int repairCost);

  /**
   * Gets the current repair cost of the container.
   *
   * @return the container's repair cost
   * @since 1.0.0
   */
  int getRepairCost();
}
