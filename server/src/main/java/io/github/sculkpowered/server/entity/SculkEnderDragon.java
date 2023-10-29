package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkEnderDragon extends AbstractMob implements EnderDragon {

  @Override
  public @NotNull EntityType type() {
    return EntityType.ENDER_DRAGON;
  }
}
