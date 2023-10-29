package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkRabbit extends AbstractAnimal implements Rabbit {

  @Override
  public @NotNull EntityType type() {
    return EntityType.RABBIT;
  }
}
