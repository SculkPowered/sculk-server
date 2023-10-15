package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkRavager extends AbstractRaider implements Ravager {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.RAVAGER;
  }
}
