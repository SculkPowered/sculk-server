package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkShulker extends AbstractMob implements Shulker {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.SHULKER;
  }
}
