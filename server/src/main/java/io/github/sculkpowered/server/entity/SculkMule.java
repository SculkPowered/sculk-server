package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkMule extends AbstractAnimal implements Mule {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.MULE;
  }
}
