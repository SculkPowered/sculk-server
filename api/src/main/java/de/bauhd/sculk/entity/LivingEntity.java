package de.bauhd.sculk.entity;

/**
 * Represents a living entity.
 */
public interface LivingEntity extends Entity {

    /**
     * Gets the health of the entity.
     *
     * @return the health of this entity
     * @since 1.0.0
     */
    float getHealth();

    /**
     * Sets the health of the entity.
     *
     * @param health the health to set
     * @since 1.0.0
     */
    void setHealth(float health);

    /**
     * Gets the potion effect color of the entity.
     *
     * @return the potion effect color of the entity
     * @since 1.0.0
     */
    int getPotionEffectColor();

    /**
     * Sets the potion effect color of the entity.
     *
     * @param effectColor the effect color to set
     * @since 1.0.0
     */
    void setPotionEffectColor(int effectColor);

    /**
     * Gets whether the player has a potion effect ambient.
     *
     * @return whether the player has a potion effect ambient
     * @since 1.0.0
     */
    boolean isPotionEffectAmbient();

    /**
     * Sets whether the player has a potion effect ambient.
     *
     * @param ambient whether the player has a potion effect ambient
     * @since 1.0.0
     */
    void setPotionEffectAmbient(boolean ambient);

    /**
     * Gets the number of arrows in the entity.
     *
     * @return the number of arrows in the entity
     * @since 1.0.0
     */
    int getNumberOfArrows();

    /**
     * Sets the number of arrows in the entity.
     *
     * @param arrows the number of arrows
     * @since 1.0.0
     */
    void setNumberOfArrows(int arrows);

    /**
     * Gets the number of bee stingers in the entity.
     *
     * @return the number of bee stingers in the entity
     * @since 1.0.0
     */
    int getNumberOfBeeStingers();

    /**
     * Sets the number of bee stingers in the entity.
     *
     * @param beeStingers the number of bee stingers
     * @since 1.0.0
     */
    void setNumberOfBeeStingers(int beeStingers);
}
