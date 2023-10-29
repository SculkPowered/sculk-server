package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkSlime extends AbstractMob implements Slime {

  @Override
  public @NotNull EntityType type() {
    return EntityType.SLIME;
  }

  @Override
  public int size() {
    return this.metadata.getVarInt(16, 1);
  }

  @Override
  public void size(int size) {
    this.metadata.setVarInt(16, size);
  }
}
