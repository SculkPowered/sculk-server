package io.github.sculkpowered.server.entity;

import java.util.UUID;

public abstract class AbstractLivingEntity extends AbstractEntity implements LivingEntity {

  public AbstractLivingEntity() {
  }

  public AbstractLivingEntity(UUID uuid) {
    super(uuid);
  }

  @Override
  public float health() {
    return this.metadata.getFloat(9, 1);
  }

  @Override
  public void health(float health) {
    this.metadata.setFloat(9, health);
  }

  @Override
  public int potionEffectColor() {
    return this.metadata.getVarInt(10, 0);
  }

  @Override
  public void potionEffectColor(int effectColor) {
    this.metadata.setVarInt(10, effectColor);
  }

  @Override
  public boolean isPotionEffectAmbient() {
    return this.metadata.getBoolean(11, false);
  }

  @Override
  public void potionEffectAmbient(boolean ambient) {
    this.metadata.setBoolean(11, ambient);
  }

  @Override
  public int numberOfArrows() {
    return this.metadata.getVarInt(12, 0);
  }

  @Override
  public void numberOfArrows(int arrows) {
    this.metadata.setVarInt(12, arrows);
  }

  @Override
  public int numberOfBeeStingers() {
    return this.metadata.getVarInt(13, 0);
  }

  @Override
  public void numberOfBeeStingers(int beeStingers) {
    this.metadata.setVarInt(13, beeStingers);
  }
}
