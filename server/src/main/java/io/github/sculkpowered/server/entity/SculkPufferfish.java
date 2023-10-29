package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkPufferfish extends AbstractMob implements Pufferfish {

  @Override
  public @NotNull EntityType type() {
    return EntityType.PUFFERFISH;
  }
}
