package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
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
