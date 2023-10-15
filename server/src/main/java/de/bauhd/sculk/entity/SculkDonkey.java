package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkDonkey extends AbstractAnimal implements Donkey {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.DONKEY;
  }
}
