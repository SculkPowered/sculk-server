package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkShulkerBullet extends AbstractEntity implements ShulkerBullet {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.SHULKER_BULLET;
  }
}
