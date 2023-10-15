package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkEyeOfEnder extends AbstractEntity implements EyeOfEnder {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.EYE_OF_ENDER;
  }
}
