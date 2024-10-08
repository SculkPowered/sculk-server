package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkBreezeWindCharge extends AbstractEntity implements BreezeWindCharge {

  public SculkBreezeWindCharge(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.BREEZE_WIND_CHARGE;
  }
}
