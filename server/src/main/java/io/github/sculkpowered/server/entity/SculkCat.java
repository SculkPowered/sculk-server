package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkCat extends AbstractTameableAnimal implements Cat {

  @Override
  public @NotNull EntityType type() {
    return EntityType.CAT;
  }
}
