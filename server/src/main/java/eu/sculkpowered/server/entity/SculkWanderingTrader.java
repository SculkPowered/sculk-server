package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkWanderingTrader extends AbstractMob implements WanderingTrader {

  public SculkWanderingTrader(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.WANDERING_TRADER;
  }
}
