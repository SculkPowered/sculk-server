package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkBlaze extends AbstractMob implements Blaze {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.BLAZE;
  }
}
