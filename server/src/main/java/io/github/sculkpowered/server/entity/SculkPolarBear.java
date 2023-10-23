package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkPolarBear extends AbstractAnimal implements PolarBear {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.POLAR_BEAR;
  }
}