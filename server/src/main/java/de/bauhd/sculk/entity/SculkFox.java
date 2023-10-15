package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkFox extends AbstractAnimal implements Fox {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.FOX;
  }
}
