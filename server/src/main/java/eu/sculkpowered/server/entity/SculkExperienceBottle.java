package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkExperienceBottle extends AbstractEntity implements ExperienceBottle {

  public SculkExperienceBottle(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.EXPERIENCE_BOTTLE;
  }
}
