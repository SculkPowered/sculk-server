package de.bauhd.sculk.entity;

/**
 * Represents a goat entity.
 */
public interface Goat extends Animal {

    /**
     * @since 1.0.0
     */
    boolean isScreamingGoat();

    /**
     * @since 1.0.0
     */
    void setScreamingGoat(boolean screamingGoat);

    /**
     * @since 1.0.0
     */
    boolean hasLeftHorn();

    /**
     * @since 1.0.0
     */
    void setLeftHorn(boolean leftHorn);

    /**
     * @since 1.0.0
     */
    boolean hasRightHorn();

    /**
     * @since 1.0.0
     */
    void setRightHorn(boolean rightHorn);
}