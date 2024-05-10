package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkOminousItemSpawner extends AbstractEntity implements OminousItemSpawner {

  public SculkOminousItemSpawner(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.BREEZE_WIND_CHARGE;
  }
}
