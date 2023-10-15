package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public class SculkBoat extends AbstractEntity implements Boat {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.BOAT;
  }
}
