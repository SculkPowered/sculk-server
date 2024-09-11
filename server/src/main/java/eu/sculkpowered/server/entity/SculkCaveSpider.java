package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkCaveSpider extends AbstractEntity implements CaveSpider {

  public SculkCaveSpider(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.CAVE_SPIDER;
  }
}
