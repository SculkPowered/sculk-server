package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkCod extends AbstractAnimal implements Cod {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.COD;
  }
}
