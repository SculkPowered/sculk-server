package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public class SculkArrow extends AbstractEntity implements Arrow {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.ARROW;
  }
}
