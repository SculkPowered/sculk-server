package de.bauhd.minecraft.server.entity;

/**
 * Represents a living entity.
 */
public interface LivingEntity extends Entity {

    /**
     * Gets the health of the entity.
     * @return the health of this entity
     */
    float getHealth();

    /**
     * Sets the health of the entity.
     * @param health the health to set
     */
    void setHealth(float health);

    /**
     * Gets the potion effect color of the entity.
     * @return the potion effect color of the entity
     */
    int getPotionEffectColor();

    /**
     * Sets the potion effect color of the entity.
     * @param effectColor the effect color to set
     */
    void setPotionEffectColor(int effectColor);

    /**
     * Gets whether the player has a potion effect ambient.
     * @return whether the player has a potion effect ambient
     */
    boolean isPotionEffectAmbient();

    /**
     * Sets whether the player has a potion effect ambient.
     * @param ambient whether the player has a potion effect ambient
     */
    void setPotionEffectAmbient(boolean ambient);

    /**
     * Gets the number of arrows in the entity.
     * @return the number of arrows in the entity
     */
    int getNumberOfArrows();

    /**
     * Sets the number of arrows in the entity.
     * @param arrows the number of arrows
     */
    void setNumberOfArrows(int arrows);

    /**
     * Gets the number of bee stingers in the entity.
     * @return the number of bee stingers in the entity
     */
    int getNumberOfBeeStingers();

    /**
     * Sets the number of bee stingers in the entity.
     * @param beeStingers the number of bee stingers
     */
    void setNumberOfBeeStingers(int beeStingers);
}
