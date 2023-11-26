package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkSpider extends AbstractMob implements Spider {

  public SculkSpider(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.SPIDER;
  }
}
