package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkStray extends SculkSkeleton implements Stray {

  @Override
  public @NotNull EntityType type() {
    return EntityType.STRAY;
  }
}
