package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkPhantom extends AbstractMob implements Phantom {

  @Override
  public @NotNull EntityType type() {
    return EntityType.PHANTOM;
  }
}
