package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkFireball extends AbstractEntity implements Fireball {

  @Override
  public @NotNull EntityType type() {
    return EntityType.FIREBALL;
  }
}
