package de.bauhd.minecraft.server.entity;

public abstract class AbstractLivingEntity extends AbstractEntity implements LivingEntity {

    @Override
    public float getHealth() {
        return this.metadata.getFloat(9, 1);
    }

    @Override
    public void setHealth(float health) {
        this.metadata.setFloat(9, health);
    }

    @Override
    public int getPotionEffectColor() {
        return this.metadata.getVarInt(10, 0);
    }

    @Override
    public void setPotionEffectColor(int effectColor) {
        this.metadata.setVarInt(10, effectColor);
    }

    @Override
    public boolean isPotionEffectAmbient() {
        return this.metadata.getBoolean(11, false);
    }

    @Override
    public void setPotionEffectAmbient(boolean ambient) {
        this.metadata.setBoolean(11, ambient);
    }

    @Override
    public int getNumberOfArrows() {
        return this.metadata.getVarInt(12, 0);
    }

    @Override
    public void setNumberOfArrows(int arrows) {
        this.metadata.setVarInt(12, arrows);
    }

    @Override
    public int getNumberOfBeeStingers() {
        return this.metadata.getVarInt(13, 0);
    }

    @Override
    public void setNumberOfBeeStingers(int beeStingers) {
        this.metadata.setVarInt(13, beeStingers);
    }
}
