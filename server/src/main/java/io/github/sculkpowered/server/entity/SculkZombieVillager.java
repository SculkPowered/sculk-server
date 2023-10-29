package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkZombieVillager extends SculkZombie implements ZombieVillager {

  @Override
  public @NotNull EntityType type() {
    return EntityType.ZOMBIE_VILLAGER;
  }
}
