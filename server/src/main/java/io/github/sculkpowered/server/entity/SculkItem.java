package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkItem extends AbstractEntity implements Item {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.ITEM;
  }
}
