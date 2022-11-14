package de.bauhd.minecraft.server.api.entity;

public interface ArmorStand extends LivingEntity {

    boolean isSmall();

    void setSmall(boolean small);

    boolean hasArms();

    void setArms(boolean arms);

    boolean hasBasePlate();

    void setBasePlate(boolean basePlate);

    boolean isMasker();

    void setMarker(boolean marker);

}
