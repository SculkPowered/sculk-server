package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkExperienceBottle extends AbstractEntity implements ExperienceBottle {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.EXPERIENCE_BOTTLE;
  }
}
