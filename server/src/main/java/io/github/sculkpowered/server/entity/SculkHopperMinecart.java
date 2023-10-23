package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkHopperMinecart extends AbstractEntity implements HopperMinecart {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.HOPPER_MINECART;
  }
}