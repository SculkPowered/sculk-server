package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public class SculkZombie extends AbstractMob implements Zombie {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.ZOMBIE;
  }

  @Override
  public boolean isBaby() {
    return this.metadata.getBoolean(16, false);
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
    this.metadata.setBoolean(16, baby);
  }
}
