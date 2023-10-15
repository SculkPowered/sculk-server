package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkLightningBolt extends AbstractEntity {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.LIGHTNING_BOLT;
  }
}
