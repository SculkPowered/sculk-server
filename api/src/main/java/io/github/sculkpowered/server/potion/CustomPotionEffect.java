package io.github.sculkpowered.server.potion;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record CustomPotionEffect(
    @NotNull MobEffectType effect,
    @NotNull Properties properties
) {

  public record Properties(
      byte amplifier,
      int duration,
      boolean ambient,
      boolean showParticles,
      boolean showIcon,
      @Nullable Properties hiddenProperties
  ) {

  }
}
