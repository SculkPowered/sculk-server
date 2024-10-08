package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkCommandBlockMinecart extends AbstractEntity implements
    CommandBlockMinecart {

  public SculkCommandBlockMinecart(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.COMMAND_BLOCK_MINECART;
  }
}
