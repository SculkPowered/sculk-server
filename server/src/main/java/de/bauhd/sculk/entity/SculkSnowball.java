package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkSnowball extends AbstractEntity implements Snowball {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.SNOWBALL;
  }
}
