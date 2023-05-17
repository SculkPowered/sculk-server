package de.bauhd.minecraft.server.entity;

public interface LivingEntity extends Entity {

    float getHealth();

    void setHealth(float health);

    int getPotionEffectColor();

    void setPotionEffectColor(int effectColor);

    boolean isPotionEffectAmbient();

    void setPotionEffectAmbient(boolean ambient);

    int getNumberOfArrows();

    void setNumberOfArrows(int arrows);

    int getNumberOfBeeStingers();

    void setNumberOfBeeStingers(int beeStingers);
}
