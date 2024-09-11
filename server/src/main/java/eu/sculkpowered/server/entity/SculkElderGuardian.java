package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkElderGuardian extends AbstractMob implements ElderGuardian {

  public SculkElderGuardian(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.GUARDIAN;
  }
}
