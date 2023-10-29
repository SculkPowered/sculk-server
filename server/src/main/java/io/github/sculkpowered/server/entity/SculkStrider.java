package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkStrider extends AbstractAnimal implements Strider {

  @Override
  public @NotNull EntityType type() {
    return EntityType.STRIDER;
  }
}
