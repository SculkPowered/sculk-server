package eu.sculkpowered.server.entity;

/**
 * Represents a ghast entity.
 */
public interface Ghast extends Mob {

  /**
   * @since 1.0.0
   */
  boolean attacking();

  /**
   * @since 1.0.0
   */
  void attacking(boolean attacking);
}