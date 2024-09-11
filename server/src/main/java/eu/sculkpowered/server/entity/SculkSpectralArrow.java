package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public class SculkSpectralArrow extends SculkArrow implements SpectralArrow {

  public SculkSpectralArrow(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.SPECTRAL_ARROW;
  }
}
