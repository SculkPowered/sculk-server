package io.github.sculkpowered.server.entity;

/**
 * Represents a slime entity.
 */
public interface Slime extends Mob {

  /**
   * @since 1.0.0
   */
  int getSize();

  /**
   * @since 1.0.0
   */
  void setSize(int size);
}