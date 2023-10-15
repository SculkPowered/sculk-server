package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkPainting extends AbstractEntity implements Painting {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.PAINTING;
  }
}
