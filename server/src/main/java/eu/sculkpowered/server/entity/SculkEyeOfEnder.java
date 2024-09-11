package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkEyeOfEnder extends AbstractEntity implements EyeOfEnder {

  public SculkEyeOfEnder(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.EYE_OF_ENDER;
  }
}
