package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkZombieHorse extends AbstractAnimal implements ZombieHorse {

  @Override
  public @NotNull EntityType type() {
    return EntityType.ZOMBIE_HORSE;
  }
}
