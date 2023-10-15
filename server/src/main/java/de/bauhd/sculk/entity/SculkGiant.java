package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkGiant extends AbstractMob implements Giant {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.GIANT;
  }
}
