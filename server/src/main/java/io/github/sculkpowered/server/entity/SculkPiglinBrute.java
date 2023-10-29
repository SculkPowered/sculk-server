package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkPiglinBrute extends AbstractMob implements PiglinBrute {

  @Override
  public @NotNull EntityType type() {
    return EntityType.PIGLIN_BRUTE;
  }
}
