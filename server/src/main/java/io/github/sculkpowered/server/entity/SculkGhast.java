package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkGhast extends AbstractMob implements Ghast {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.GHAST;
  }

  @Override
  public boolean isAttacking() {
    return this.metadata.getBoolean(16, false);
  }

  @Override
  public void setAttacking(boolean attacking) {
    this.metadata.setBoolean(16, attacking);
  }
}
