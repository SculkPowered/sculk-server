package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkExperienceOrb extends AbstractEntity implements ExperienceOrb {

  @Override
  public @NotNull EntityType type() {
    return EntityType.EXPERIENCE_ORB;
  }
}
