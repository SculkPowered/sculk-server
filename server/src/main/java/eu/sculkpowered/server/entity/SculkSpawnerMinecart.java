package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
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
