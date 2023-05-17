package de.bauhd.minecraft.server.entity;

public interface ArmorStand extends LivingEntity {

    boolean isSmall();

    void setSmall(boolean small);

    boolean hasArms();

    void setArms(boolean arms);

    boolean hasBasePlate();

    void setBasePlate(boolean basePlate);

    boolean isMarker();

    void setMarker(boolean marker);
}
