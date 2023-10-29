package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkHoglin extends AbstractAnimal implements Hoglin {

  @Override
  public @NotNull EntityType type() {
    return EntityType.HOGLIN;
  }
}
