package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkCreeper extends AbstractMob implements Creeper {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.CREEPER;
  }

  @Override
  public boolean isIdling() {
    return this.metadata.getVarInt(16, -1) == -1;
  }

  @Override
  public void setIdling() {
    this.metadata.setVarInt(16, -1);
  }

  @Override
  public boolean isFused() {
    return this.metadata.getVarInt(16, -1) == 1;
  }

  @Override
  public void setFusing() {
    this.metadata.setVarInt(16, 1);
  }

  @Override
  public boolean isCharged() {
    return this.metadata.getBoolean(17, false);
  }

  @Override
  public void setCharged(boolean charged) {
    this.metadata.setBoolean(17, charged);
  }

  @Override
  public boolean isIgnited() {
    return this.metadata.getBoolean(18, false);
  }

  @Override
  public void setIgnited(boolean ignited) {
    this.metadata.setBoolean(18, ignited);
  }
}
