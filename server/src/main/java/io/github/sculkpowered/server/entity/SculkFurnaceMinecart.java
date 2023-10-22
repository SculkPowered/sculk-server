package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkFurnaceMinecart extends AbstractEntity implements FurnaceMinecart {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.FURNACE_MINECART;
  }
}
