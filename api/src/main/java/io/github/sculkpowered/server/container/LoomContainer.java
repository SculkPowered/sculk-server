package io.github.sculkpowered.server.container;

/**
 * Represents a container of a loom block.
 */
public interface LoomContainer extends Container {

  /**
   * @since 1.0.0
   */
  void setSelectedPattern(int pattern);

  /**
   * @since 1.0.0
   */
  int getSelectedPattern();
}
