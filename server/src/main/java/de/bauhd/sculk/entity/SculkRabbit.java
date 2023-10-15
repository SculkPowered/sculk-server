package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkRabbit extends AbstractAnimal implements Rabbit {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.RABBIT;
  }
}
