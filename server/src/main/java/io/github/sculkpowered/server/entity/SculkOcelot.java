package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkOcelot extends AbstractAnimal implements Ocelot {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.OCELOT;
  }
}
