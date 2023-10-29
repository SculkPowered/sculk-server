package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkEnderman extends AbstractMob implements Enderman {

  @Override
  public @NotNull EntityType type() {
    return EntityType.ENDERMAN;
  }
}
