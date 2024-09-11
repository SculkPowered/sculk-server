package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkPolarBear extends AbstractAnimal implements PolarBear {

  public SculkPolarBear(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.POLAR_BEAR;
  }
}
