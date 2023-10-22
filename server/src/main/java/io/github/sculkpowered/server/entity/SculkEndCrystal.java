package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkEndCrystal extends AbstractEntity implements EndCrystal {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.END_CRYSTAL;
  }
}
