package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkEvoker extends AbstractRaider implements Evoker {

  @Override
  public @NotNull EntityType type() {
    return EntityType.EVOKER;
  }
}
