package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkChestBoat extends SculkBoat {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.CHEST_BOAT;
  }
}
