package io.github.sculkpowered.server.entity;

/**
 * Represents a witch entity.
 */
public interface Witch extends Raider {

  /**
   * @since 1.0.0
   */
  boolean drinkingPotion();

  /**
   * @since 1.0.0
   */
  void drinkingPotion(boolean drinkingPotion);
}