package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public class SculkSpectralArrow extends SculkArrow implements SpectralArrow {

  @Override
  public @NotNull EntityType type() {
    return EntityType.SPECTRAL_ARROW;
  }
}
