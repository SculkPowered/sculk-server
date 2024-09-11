package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkSnowGolem extends AbstractMob implements SnowGolem {

  public SculkSnowGolem(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.SNOW_GOLEM;
  }
}
