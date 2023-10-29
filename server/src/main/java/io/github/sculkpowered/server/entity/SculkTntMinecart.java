package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkTntMinecart extends AbstractEntity implements TntMinecart {

  @Override
  public @NotNull EntityType type() {
    return EntityType.TNT_MINECART;
  }
}
