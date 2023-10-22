package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkMagmaCube extends AbstractMob implements MagmaCube {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.MAGMA_CUBE;
  }
}
