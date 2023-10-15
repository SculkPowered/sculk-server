package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkCat extends AbstractTameableAnimal implements Cat {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.CAT;
  }
}
