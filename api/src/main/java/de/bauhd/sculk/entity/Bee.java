package de.bauhd.sculk.entity;

/**
 * Represents a bee entity.
 */
public interface Bee extends Animal {

    /**
     * @since 1.0.0
     */
    boolean isAngry();

    /**
     * @since 1.0.0
     */
    void setAngry(boolean angry);

    /**
     * @since 1.0.0
     */
    boolean hasStung();

    /**
     * @since 1.0.0
     */
    void setStung(boolean stung);

    /**
     * @since 1.0.0
     */
    boolean hasNectar();

    /**
     * @since 1.0.0
     */
    void setNectar(boolean nectar);
}