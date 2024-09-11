package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkChestMinecart extends AbstractEntity implements ChestMinecart {

  public SculkChestMinecart(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.CHEST_MINECART;
  }
}
