package eu.sculkpowered.server.entity;

/**
 * Represents a slime entity.
 */
public interface Slime extends Mob {

  /**
   * @since 1.0.0
   */
  int size();

  /**
   * @since 1.0.0
   */
  void size(int size);
}