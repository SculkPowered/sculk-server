package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkMooshroom extends AbstractAnimal implements Mooshroom {

  @Override
  public @NotNull EntityType type() {
    return EntityType.MOOSHROOM;
  }
}
