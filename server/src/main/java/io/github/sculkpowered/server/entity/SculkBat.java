package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkBat extends AbstractAnimal implements Bat {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.BAT;
  }

  @Override
  public boolean isHanging() {
    return this.metadata.inMask(16, 0x01);
  }

  @Override
  public void setHanging(boolean hanging) {
    this.metadata.setMask(16, 0x01, hanging);
  }
}
