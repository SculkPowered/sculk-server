package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
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
