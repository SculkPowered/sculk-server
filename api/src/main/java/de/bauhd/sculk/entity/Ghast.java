package de.bauhd.sculk.entity;

/**
 * Represents a ghast entity.
 */
public interface Ghast extends Mob {

    /**
     * @since 1.0.0
     */
    boolean isAttacking();

    /**
     * @since 1.0.0
     */
    void setAttacking(boolean attacking);
}