package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkIronGolem extends AbstractMob implements IronGolem {

  public SculkIronGolem(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.IRON_GOLEM;
  }
}
