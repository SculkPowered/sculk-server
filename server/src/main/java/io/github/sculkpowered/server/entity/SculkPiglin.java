package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkPiglin extends AbstractMob implements Piglin {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.PIGLIN;
  }

  @Override
  public boolean isBaby() {
    return this.metadata.getBoolean(17, false);
  }

  @Override
  public void setBaby() {
    this.setBaby(true);
  }

  @Override
  public boolean isAdult() {
    return !this.isBaby();
  }

  @Override
  public void setAdult() {
    this.setBaby(false);
  }

  public void setBaby(boolean baby) {
    this.metadata.setBoolean(17, baby);
  }

  @Override
  public boolean isCharging() {
    return this.metadata.getBoolean(18, false);
  }

  @Override
  public void setCharging(boolean charging) {
    this.metadata.setBoolean(18, charging);
  }

  @Override
  public boolean isDancing() {
    return this.metadata.getBoolean(19, false);
  }

  @Override
  public void setDancing(boolean dancing) {
    this.metadata.setBoolean(19, dancing);
  }
}
