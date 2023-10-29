package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkGlowItemFrame extends SculkItemFrame implements GlowItemFrame {

  @Override
  public @NotNull EntityType type() {
    return EntityType.GLOW_ITEM_FRAME;
  }
}
