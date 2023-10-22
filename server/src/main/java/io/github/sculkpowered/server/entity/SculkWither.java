package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkWither extends AbstractMob implements Wither {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.WITHER;
  }
}
