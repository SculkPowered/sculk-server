package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
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
