package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkFox extends AbstractAnimal implements Fox {

  @Override
  public @NotNull EntityType type() {
    return EntityType.FOX;
  }
}
