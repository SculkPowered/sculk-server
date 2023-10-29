package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkCow extends AbstractAnimal implements Cow {

  @Override
  public @NotNull EntityType type() {
    return EntityType.COW;
  }
}
