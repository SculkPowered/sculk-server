package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkTurtle extends AbstractAnimal implements Turtle {

  @Override
  public @NotNull EntityType type() {
    return EntityType.TURTLE;
  }
}
