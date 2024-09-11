package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkWitherSkull extends AbstractEntity implements WitherSkull {

  public SculkWitherSkull(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.WITHER_SKULL;
  }
}
