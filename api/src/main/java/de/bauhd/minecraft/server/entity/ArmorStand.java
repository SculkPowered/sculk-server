package de.bauhd.minecraft.server.entity;

/**
 * Represents an armor stand.
 */
public interface ArmorStand extends LivingEntity {

    /**
     * Gets whether this armor stand is small or not.
     * @return whether it's small or not
     */
    boolean isSmall();

    /**
     * Sets the armor stand small.
     */
    void setSmall();

    /**
     * Gets whether this armor stand is big or not.
     * @return whether it's big or not
     */
    boolean isBig();

    /**
     * Sets the armor stand big.
     */
    void setBig();

    /**
     * Gets whether the armor stand has arms or not.
     * @return whether this armor stand has arms
     */
    boolean hasArms();

    /**
     * Sets whether the armor stand has arms.
     * @param arms whether this armor stand has arms
     */
    void setArms(boolean arms);

    /**
     * Gets whether the armor stand has a baseplate or not.
     * @return whether this armor stand has a baseplate
     */
    boolean hasBasePlate();

    /**
     * Sets whether the armor stand has a baseplate.
     * @param basePlate whether this armor stand has a baseplate
     */
    void setBasePlate(boolean basePlate);

    /**
     * Gets whether the armor stand is a marker or not.
     * @return whether this armor stand is a marker
     */
    boolean isMarker();

    /**
     * Sets whether the armor stand is a marker.
     * @param marker whether this armor stand is a marker
     */
    void setMarker(boolean marker);
}
