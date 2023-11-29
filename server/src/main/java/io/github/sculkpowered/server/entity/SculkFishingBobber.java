package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkFishingBobber extends AbstractEntity implements FishingBobber {

  public SculkFishingBobber(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.FISHING_BOBBER;
  }
}
