package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkSpawnerMinecart extends AbstractEntity implements SpawnerMinecart {

  public SculkSpawnerMinecart(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.SPAWNER_MINECART;
  }
}
