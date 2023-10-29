package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkLightningBolt extends AbstractEntity {

  @Override
  public @NotNull EntityType type() {
    return EntityType.LIGHTNING_BOLT;
  }
}
