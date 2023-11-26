package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkEnderPearl extends AbstractEntity implements EnderPearl {

  public SculkEnderPearl(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.ENDER_PEARL;
  }
}
