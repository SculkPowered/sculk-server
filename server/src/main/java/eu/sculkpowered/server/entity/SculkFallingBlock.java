package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkFallingBlock extends AbstractEntity implements FallingBlock {

  public SculkFallingBlock(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.FALLING_BLOCK;
  }
}
