package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkVex extends AbstractMob implements Vex {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.VEX;
  }
}
