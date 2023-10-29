package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkFishingBobber extends AbstractEntity implements FishingBobber {

  @Override
  public @NotNull EntityType type() {
    return EntityType.FISHING_BOBBER;
  }
}
