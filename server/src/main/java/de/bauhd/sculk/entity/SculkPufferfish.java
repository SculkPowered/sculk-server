package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkPufferfish extends AbstractMob implements Pufferfish {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.PUFFERFISH;
  }
}
