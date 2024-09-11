package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkTropicalFish extends AbstractMob implements TropicalFish {

  public SculkTropicalFish(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.TROPICAL_FISH;
  }
}
