package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;

public abstract class AbstractMob extends AbstractLivingEntity implements Mob {

  public AbstractMob(final SculkServer server) {
    super(server);
  }

  @Override
  public boolean isAggressive() {
    return this.metadata.inMask(15, 0x04);
  }
}
