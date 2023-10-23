package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkEnderPearl extends AbstractEntity implements EnderPearl {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.ENDER_PEARL;
  }
}