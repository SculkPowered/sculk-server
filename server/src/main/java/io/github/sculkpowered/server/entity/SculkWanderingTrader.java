package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkWanderingTrader extends AbstractMob implements WanderingTrader {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.WANDERING_TRADER;
  }
}
