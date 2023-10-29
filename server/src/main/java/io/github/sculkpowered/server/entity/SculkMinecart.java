package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkMinecart extends AbstractEntity implements Minecart {

  @Override
  public @NotNull EntityType type() {
    return EntityType.MINECART;
  }
}
