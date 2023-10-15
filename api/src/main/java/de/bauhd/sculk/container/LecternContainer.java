package de.bauhd.sculk.container;

/**
 * Represents a container of a lectern block.
 */
public interface LecternContainer extends Container {

  /**
   * @since 1.0.0
   */
  void setPage(int page);

  /**
   * @since 1.0.0
   */
  int getPage();
}
