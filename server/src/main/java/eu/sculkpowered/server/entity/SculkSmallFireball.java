package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
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
