package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkPig extends AbstractAnimal implements Pig {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.PIG;
  }

  @Override
  public boolean hasSaddle() {
    return this.metadata.getBoolean(17, false);
  }

  @Override
  public void setSaddle(boolean saddle) {
    this.metadata.setBoolean(17, saddle);
  }
}
