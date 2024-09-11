package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkWindCharge extends AbstractEntity implements WindCharge {

  public SculkWindCharge(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.WIND_CHARGE;
  }
}
