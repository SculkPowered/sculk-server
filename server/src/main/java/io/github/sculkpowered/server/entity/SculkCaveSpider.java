package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkCaveSpider extends AbstractEntity implements CaveSpider {

  @Override
  public @NotNull EntityType type() {
    return EntityType.CAVE_SPIDER;
  }
}
