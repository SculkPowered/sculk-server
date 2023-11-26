package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkSkeletonHorse extends AbstractAnimal implements SkeletonHorse {

  public SculkSkeletonHorse(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.SKELETON_HORSE;
  }
}
