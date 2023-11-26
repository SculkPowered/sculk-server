package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkSnowGolem extends AbstractMob implements SnowGolem {

  public SculkSnowGolem(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.SNOW_GOLEM;
  }
}
