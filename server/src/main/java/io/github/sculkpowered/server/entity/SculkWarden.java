package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkWarden extends AbstractMob implements Warden {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.WARDEN;
  }
}
