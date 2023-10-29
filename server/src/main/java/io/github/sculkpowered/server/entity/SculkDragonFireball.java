package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkDragonFireball extends AbstractEntity implements DragonFireball {

  @Override
  public @NotNull EntityType type() {
    return EntityType.FIREBALL;
  }
}
