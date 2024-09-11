package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkEndCrystal extends AbstractEntity implements EndCrystal {

  public SculkEndCrystal(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.END_CRYSTAL;
  }
}
