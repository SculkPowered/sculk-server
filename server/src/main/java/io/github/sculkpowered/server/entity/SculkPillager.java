package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkPillager extends AbstractRaider implements Pillager {

  @Override
  public @NotNull EntityType type() {
    return EntityType.PILLAGER;
  }

  @Override
  public boolean charging() {
    return this.metadata.getBoolean(17, false);
  }

  @Override
  public void charging(boolean charging) {
    this.metadata.setBoolean(17, charging);
  }
}
