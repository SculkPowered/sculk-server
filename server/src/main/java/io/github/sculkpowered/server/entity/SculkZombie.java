package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public class SculkZombie extends AbstractMob implements Zombie {

  @Override
  public @NotNull EntityType type() {
    return EntityType.ZOMBIE;
  }

  @Override
  public boolean baby() {
    return this.metadata.getBoolean(16, false);
  }

  @Override
  public boolean adult() {
    return !this.baby();
  }

  @Override
  public void setBaby(boolean baby) {
    this.metadata.setBoolean(16, baby);
  }
}
