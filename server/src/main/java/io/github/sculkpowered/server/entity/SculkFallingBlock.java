package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkFallingBlock extends AbstractEntity implements FallingBlock {

  @Override
  public @NotNull EntityType type() {
    return EntityType.FALLING_BLOCK;
  }
}
