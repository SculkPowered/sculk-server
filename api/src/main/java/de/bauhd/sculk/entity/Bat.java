package de.bauhd.sculk.entity;

/**
 * Represents a bat entity.
 */
public interface Bat extends Mob {

    /**
     * @since 1.0.0
     */
    boolean isHanging();

    /**
     * @since 1.0.0
     */
    void setHanging(boolean hanging);
}