package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
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
