package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkInteraction extends AbstractEntity implements Interaction {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.INTERACTION;
  }
}