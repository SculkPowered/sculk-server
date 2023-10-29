package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkPanda extends AbstractAnimal implements Panda {

  @Override
  public @NotNull EntityType type() {
    return EntityType.PANDA;
  }
}
