package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkTropicalFish extends AbstractMob implements TropicalFish {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.TROPICAL_FISH;
  }
}
