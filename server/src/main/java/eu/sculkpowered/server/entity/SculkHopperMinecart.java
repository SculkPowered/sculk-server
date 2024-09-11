package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
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
