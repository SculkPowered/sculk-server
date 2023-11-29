package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkSmallFireball extends AbstractEntity implements SmallFireball {

  public SculkSmallFireball(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.SMALL_FIREBALL;
  }
}
