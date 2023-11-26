package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
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
