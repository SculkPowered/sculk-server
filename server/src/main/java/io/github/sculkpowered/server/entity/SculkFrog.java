package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkFrog extends AbstractAnimal implements Frog {

  @Override
  public @NotNull EntityType type() {
    return EntityType.FROG;
  }
}
