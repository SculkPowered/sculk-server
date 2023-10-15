package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkCamel extends AbstractAnimal implements Camel {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.CAMEL;
  }
}
