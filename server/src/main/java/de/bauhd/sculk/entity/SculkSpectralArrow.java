package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public class SculkSpectralArrow extends SculkArrow implements SpectralArrow {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.SPECTRAL_ARROW;
  }
}
