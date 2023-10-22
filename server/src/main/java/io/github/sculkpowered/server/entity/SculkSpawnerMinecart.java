package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkSpawnerMinecart extends AbstractEntity implements SpawnerMinecart {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.SPAWNER_MINECART;
  }
}
