package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
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
