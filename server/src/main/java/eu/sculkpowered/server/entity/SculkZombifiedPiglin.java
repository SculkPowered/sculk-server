package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkZombifiedPiglin extends SculkZombie implements ZombifiedPiglin {

  public SculkZombifiedPiglin(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.ZOMBIFIED_PIGLIN;
  }
}
