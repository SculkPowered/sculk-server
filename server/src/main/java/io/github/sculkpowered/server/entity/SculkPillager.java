package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkPillager extends AbstractRaider implements Pillager {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.PILLAGER;
  }

  @Override
  public boolean isCharging() {
    return this.metadata.getBoolean(17, false);
  }

  @Override
  public void setCharging(boolean charging) {
    this.metadata.setBoolean(17, charging);
  }
}
