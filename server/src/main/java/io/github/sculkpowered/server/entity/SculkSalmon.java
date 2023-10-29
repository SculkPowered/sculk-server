package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkSalmon extends AbstractMob implements Salmon {

  @Override
  public @NotNull EntityType type() {
    return EntityType.SALMON;
  }
}
