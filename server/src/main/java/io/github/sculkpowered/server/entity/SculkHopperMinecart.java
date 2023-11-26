package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkHopperMinecart extends AbstractEntity implements HopperMinecart {

  public SculkHopperMinecart(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.HOPPER_MINECART;
  }
}
