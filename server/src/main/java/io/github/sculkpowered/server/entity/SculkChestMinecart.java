package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkChestMinecart extends AbstractEntity implements ChestMinecart {

  @Override
  public @NotNull EntityType type() {
    return EntityType.CHEST_MINECART;
  }
}
