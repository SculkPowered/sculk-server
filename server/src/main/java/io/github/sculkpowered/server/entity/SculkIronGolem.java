package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkIronGolem extends AbstractMob implements IronGolem {

  @Override
  public @NotNull EntityType type() {
    return EntityType.IRON_GOLEM;
  }
}
