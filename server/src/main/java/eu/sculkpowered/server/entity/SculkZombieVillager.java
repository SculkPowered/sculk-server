package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkZombieVillager extends SculkZombie implements ZombieVillager {

  public SculkZombieVillager(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.ZOMBIE_VILLAGER;
  }
}
