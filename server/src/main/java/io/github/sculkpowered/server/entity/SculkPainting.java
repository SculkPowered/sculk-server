package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkPainting extends AbstractEntity implements Painting {

  @Override
  public @NotNull EntityType type() {
    return EntityType.PAINTING;
  }
}
