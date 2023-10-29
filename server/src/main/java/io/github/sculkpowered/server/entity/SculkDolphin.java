package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkDolphin extends AbstractMob implements Dolphin {

  @Override
  public @NotNull EntityType type() {
    return EntityType.DOLPHIN;
  }
}
