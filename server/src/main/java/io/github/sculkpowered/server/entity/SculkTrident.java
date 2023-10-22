package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkTrident extends SculkArrow implements Trident {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.TRIDENT;
  }
}
