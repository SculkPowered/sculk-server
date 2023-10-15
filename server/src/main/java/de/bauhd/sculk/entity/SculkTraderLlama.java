package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkTraderLlama extends SculkLlama implements TraderLlama {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.TRADER_LLAMA;
  }
}
