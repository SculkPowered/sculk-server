package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkRavager extends AbstractRaider implements Ravager {

  @Override
  public @NotNull EntityType type() {
    return EntityType.RAVAGER;
  }
}
