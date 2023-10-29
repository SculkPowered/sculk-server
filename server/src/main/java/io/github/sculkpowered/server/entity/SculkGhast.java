package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkGhast extends AbstractMob implements Ghast {

  @Override
  public @NotNull EntityType type() {
    return EntityType.GHAST;
  }

  @Override
  public boolean attacking() {
    return this.metadata.getBoolean(16, false);
  }

  @Override
  public void attacking(boolean attacking) {
    this.metadata.setBoolean(16, attacking);
  }
}
