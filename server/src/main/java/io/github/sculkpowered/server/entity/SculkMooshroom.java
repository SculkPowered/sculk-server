package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkMooshroom extends AbstractAnimal implements Mooshroom {

  public SculkMooshroom(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.MOOSHROOM;
  }
}
