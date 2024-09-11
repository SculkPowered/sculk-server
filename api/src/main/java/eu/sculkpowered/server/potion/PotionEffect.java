package eu.sculkpowered.server.potion;

import org.jetbrains.annotations.NotNull;

public record PotionEffect(
  @NotNull MobEffectType effect,
  int duration
) {

}
