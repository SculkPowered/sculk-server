package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkGlowItemFrame extends SculkItemFrame implements GlowItemFrame {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.GLOW_ITEM_FRAME;
  }
}
