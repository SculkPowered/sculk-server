package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkTraderLlama extends SculkLlama implements TraderLlama {

  @Override
  public @NotNull EntityType type() {
    return EntityType.TRADER_LLAMA;
  }
}
