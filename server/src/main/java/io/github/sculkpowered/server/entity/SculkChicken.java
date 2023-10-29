package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkChicken extends AbstractAnimal implements Chicken {

  @Override
  public @NotNull EntityType type() {
    return EntityType.CHICKEN;
  }
}
