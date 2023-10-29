package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkCamel extends AbstractAnimal implements Camel {

  @Override
  public @NotNull EntityType type() {
    return EntityType.CAMEL;
  }
}
