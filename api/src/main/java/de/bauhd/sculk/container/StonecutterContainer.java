package de.bauhd.sculk.container;

/**
 * Represents a container of a stone cutter.
 */
public interface StonecutterContainer extends Container {

  /**
   * @since 1.0.0
   */
  void setSelectedRecipe(int recipe);

  /**
   * @since 1.0.0
   */
  int getSelectedRecipe();
}
