package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkFireworkRocket extends AbstractEntity implements FireworkRocket {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.FIREWORK_ROCKET;
  }
}
