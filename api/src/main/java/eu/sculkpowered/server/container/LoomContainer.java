package eu.sculkpowered.server.container;

/**
 * Represents a container of a loom block.
 */
public interface LoomContainer extends Container {

  /**
   * @since 1.0.0
   */
  void selectedPattern(int pattern);

  /**
   * @since 1.0.0
   */
  int selectedPattern();
}
