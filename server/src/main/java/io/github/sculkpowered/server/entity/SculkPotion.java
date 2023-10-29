package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkPotion extends AbstractEntity implements Potion {

  @Override
  public @NotNull EntityType type() {
    return EntityType.POTION;
  }
}
