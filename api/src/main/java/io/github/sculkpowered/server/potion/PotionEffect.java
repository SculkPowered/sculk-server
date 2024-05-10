package io.github.sculkpowered.server.potion;

import org.jetbrains.annotations.NotNull;

public record PotionEffect(
  @NotNull MobEffectType effect,
  int duration
) {

}
