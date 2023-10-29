package io.github.sculkpowered.server.container;

/**
 * Represents a container of a lectern block.
 */
public interface LecternContainer extends Container {

  /**
   * @since 1.0.0
   */
  void page(int page);

  /**
   * @since 1.0.0
   */
  int page();
}
