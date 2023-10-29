package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkEyeOfEnder extends AbstractEntity implements EyeOfEnder {

  @Override
  public @NotNull EntityType type() {
    return EntityType.EYE_OF_ENDER;
  }
}
