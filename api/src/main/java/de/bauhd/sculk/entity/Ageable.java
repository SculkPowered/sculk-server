package de.bauhd.sculk.entity;

/**
 * Represents an entity that can age.
 */
public interface Ageable {

    /**
     * Gets if the entity is a baby or not.
     * @return true if it is a baby
     */
    boolean isBaby();

    /**
     * Sets the entity to a baby.
     */
    void setBaby();

    /**
     * Gets if the entity is an adult or not.
     * @return true if it is an adult
     */
    boolean isAdult();

    /**
     * Sets the entity to an adult.
     */
    void setAdult();
}
