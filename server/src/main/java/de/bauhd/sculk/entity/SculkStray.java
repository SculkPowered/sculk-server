package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkStray extends SculkSkeleton implements Stray {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.STRAY;
  }
}
