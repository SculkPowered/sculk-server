package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkElderGuardian extends AbstractMob implements ElderGuardian {

  @Override
  public @NotNull EntityType type() {
    return EntityType.GUARDIAN;
  }
}
