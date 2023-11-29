package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkEvokerFangs extends AbstractEntity implements EvokerFangs {

  public SculkEvokerFangs(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.EVOKER_FANGS;
  }
}
