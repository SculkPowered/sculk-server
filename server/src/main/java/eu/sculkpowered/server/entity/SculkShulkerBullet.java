package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkShulkerBullet extends AbstractEntity implements ShulkerBullet {

  public SculkShulkerBullet(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.SHULKER_BULLET;
  }
}
