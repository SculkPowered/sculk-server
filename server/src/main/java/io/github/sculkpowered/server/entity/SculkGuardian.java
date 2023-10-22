package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkGuardian extends AbstractMob implements Guardian {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.GUARDIAN;
  }
}
