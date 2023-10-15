package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkCaveSpider extends AbstractEntity implements CaveSpider {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.CAVE_SPIDER;
  }
}
