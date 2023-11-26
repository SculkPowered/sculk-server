package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkZombieHorse extends AbstractAnimal implements ZombieHorse {

  public SculkZombieHorse(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.ZOMBIE_HORSE;
  }
}
