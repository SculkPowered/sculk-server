package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkWitherSkull extends AbstractEntity implements WitherSkull {

  @Override
  public @NotNull EntityType type() {
    return EntityType.WITHER_SKULL;
  }
}
