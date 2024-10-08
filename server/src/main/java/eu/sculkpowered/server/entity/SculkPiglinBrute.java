package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkPiglinBrute extends AbstractMob implements PiglinBrute {

  public SculkPiglinBrute(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.PIGLIN_BRUTE;
  }
}
