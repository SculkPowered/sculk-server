package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkSilverfish extends AbstractMob implements Silverfish {

  @Override
  public @NotNull EntityType type() {
    return EntityType.SILVERFISH;
  }
}
