package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkDolphin extends AbstractMob implements Dolphin {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.DOLPHIN;
  }
}
