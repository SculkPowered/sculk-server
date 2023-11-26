package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkWitherSkeleton extends AbstractMob implements WitherSkeleton {

  public SculkWitherSkeleton(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.WITHER_SKELETON;
  }
}
