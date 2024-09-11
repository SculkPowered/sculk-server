package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
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
