package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkSmallFireball extends AbstractEntity implements SmallFireball {

  @Override
  public @NotNull EntityType type() {
    return EntityType.SMALL_FIREBALL;
  }
}
