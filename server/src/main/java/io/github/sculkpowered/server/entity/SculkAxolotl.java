package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkAxolotl extends AbstractAnimal implements Axolotl {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.AXOLOTL;
  }

  @Override
  public @NotNull Axolotl.Variant getVariant() {
    return this.metadata.getEnum(17, Variant.LUCY);
  }

  @Override
  public void setVariant(@NotNull Variant variant) {
    this.metadata.setVarInt(17, variant.ordinal());
  }

  @Override
  public boolean isPlayingDead() {
    return this.metadata.getBoolean(18, false);
  }

  @Override
  public void setPlayingDead(boolean playingDead) {
    this.metadata.setBoolean(18, playingDead);
  }
}
