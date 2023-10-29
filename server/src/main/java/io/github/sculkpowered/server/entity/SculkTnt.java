package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkTnt extends AbstractEntity implements Tnt {

  @Override
  public @NotNull EntityType type() {
    return EntityType.TNT;
  }
}
