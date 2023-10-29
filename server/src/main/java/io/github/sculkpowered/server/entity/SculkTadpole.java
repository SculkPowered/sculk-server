package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkTadpole extends AbstractMob implements Tadpole {

  @Override
  public @NotNull EntityType type() {
    return EntityType.TADPOLE;
  }
}
