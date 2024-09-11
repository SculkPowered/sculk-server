package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkMagmaCube extends AbstractMob implements MagmaCube {

  public SculkMagmaCube(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.MAGMA_CUBE;
  }
}
