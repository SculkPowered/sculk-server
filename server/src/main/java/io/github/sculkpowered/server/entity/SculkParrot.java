package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkParrot extends AbstractTameableAnimal implements Parrot {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.PARROT;
  }
}
