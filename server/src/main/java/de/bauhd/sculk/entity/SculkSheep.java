package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkSheep extends AbstractAnimal implements Sheep {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.SHEEP;
  }
}
