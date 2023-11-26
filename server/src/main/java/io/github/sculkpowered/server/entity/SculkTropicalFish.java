package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkTropicalFish extends AbstractMob implements TropicalFish {

  public SculkTropicalFish(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.TROPICAL_FISH;
  }
}
