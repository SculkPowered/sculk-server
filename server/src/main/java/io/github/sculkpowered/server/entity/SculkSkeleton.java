package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public class SculkSkeleton extends AbstractMob implements Skeleton {

  public SculkSkeleton(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.SKELETON;
  }
}
