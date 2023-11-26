package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkWither extends AbstractMob implements Wither {

  public SculkWither(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.WITHER;
  }
}
