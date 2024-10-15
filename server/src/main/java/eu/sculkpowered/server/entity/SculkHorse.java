package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkHorse extends AbstractAnimal implements Horse {

  public SculkHorse(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.HORSE;
  }
}