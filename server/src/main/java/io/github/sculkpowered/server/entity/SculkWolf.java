package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkWolf extends AbstractTameableAnimal implements Wolf {

  @Override
  public @NotNull EntityType type() {
    return EntityType.WOLF;
  }
}
