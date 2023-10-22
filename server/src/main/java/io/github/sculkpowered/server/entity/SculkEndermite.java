package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkEndermite extends AbstractMob implements Endermite {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.ENDERMITE;
  }
}
