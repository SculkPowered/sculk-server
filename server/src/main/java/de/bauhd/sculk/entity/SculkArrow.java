package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public class SculkArrow extends AbstractEntity implements Arrow {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.ARROW;
  }
}
