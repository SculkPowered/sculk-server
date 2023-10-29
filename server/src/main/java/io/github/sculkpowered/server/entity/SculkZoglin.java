package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkZoglin extends AbstractMob implements Zoglin {

  @Override
  public @NotNull EntityType type() {
    return EntityType.ZOGLIN;
  }

  @Override
  public boolean baby() {
    return this.metadata.getBoolean(17, false);
  }

  @Override
  public boolean adult() {
    return !this.baby();
  }

  @Override
  public void setBaby(boolean baby) {
    this.metadata.setBoolean(17, baby);
  }
}
