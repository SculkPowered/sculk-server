package io.github.sculkpowered.server.entity;

public interface Raider extends Monster {

  /**
   * @since 1.0.0
   */
  boolean isCelebrating();

  /**
   * @since 1.0.0
   */
  void setCelebrating(boolean celebrating);
}
