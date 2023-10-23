package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkGlowSquid extends AbstractAnimal implements Squid {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.SQUID;
  }
}