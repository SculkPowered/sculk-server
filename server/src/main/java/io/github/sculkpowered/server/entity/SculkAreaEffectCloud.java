package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkAreaEffectCloud extends AbstractEntity implements AreaEffectCloud {

  @Override
  public @NotNull EntityType type() {
    return EntityType.AREA_EFFECT_CLOUD;
  }
}
