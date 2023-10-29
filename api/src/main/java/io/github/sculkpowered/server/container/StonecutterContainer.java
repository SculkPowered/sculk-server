package io.github.sculkpowered.server.container;

/**
 * Represents a container of a stone cutter.
 */
public interface StonecutterContainer extends Container {

  /**
   * @since 1.0.0
   */
  void selectedRecipe(int recipe);

  /**
   * @since 1.0.0
   */
  int selectedRecipe();
}
