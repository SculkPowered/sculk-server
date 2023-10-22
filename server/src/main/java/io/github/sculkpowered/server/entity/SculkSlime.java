package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkSlime extends AbstractMob implements Slime {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.SLIME;
  }

  @Override
  public int getSize() {
    return this.metadata.getVarInt(16, 1);
  }

  @Override
  public void setSize(int size) {
    this.metadata.setVarInt(16, size);
  }
}
