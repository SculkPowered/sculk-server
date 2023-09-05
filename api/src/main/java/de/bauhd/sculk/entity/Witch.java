package de.bauhd.sculk.entity;

/**
 * Represents a witch entity.
 */
public interface Witch extends Raider {

    /**
     * @since 1.0.0
     */
    boolean isDrinkingPotion();

    /**
     * @since 1.0.0
     */
    void setDrinkingPotion(boolean drinkingPotion);
}