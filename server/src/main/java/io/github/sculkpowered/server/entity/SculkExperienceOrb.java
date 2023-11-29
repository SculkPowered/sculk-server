package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkExperienceOrb extends AbstractEntity implements ExperienceOrb {

  public SculkExperienceOrb(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.EXPERIENCE_ORB;
  }
}
