package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkDragonFireball extends AbstractEntity implements DragonFireball {

  public SculkDragonFireball(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.FIREBALL;
  }
}
