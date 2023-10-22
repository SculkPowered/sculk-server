package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkCommandBlockMinecart extends AbstractEntity implements
    CommandBlockMinecart {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.COMMAND_BLOCK_MINECART;
  }
}
