package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkFireworkRocket extends AbstractEntity implements FireworkRocket {

  @Override
  public @NotNull EntityType type() {
    return EntityType.FIREWORK_ROCKET;
  }
}
