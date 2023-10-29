package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkCod extends AbstractAnimal implements Cod {

  @Override
  public @NotNull EntityType type() {
    return EntityType.COD;
  }
}
