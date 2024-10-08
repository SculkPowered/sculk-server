package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkPiglin extends AbstractMob implements Piglin {

  public SculkPiglin(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.PIGLIN;
  }

  @Override
  public boolean baby() {
    return this.metadata.getBoolean(17, false);
  }

  @Override
  public boolean adult() {
    return !this.baby();
  }

  @Override
  public void setBaby(boolean baby) {
    this.metadata.setBoolean(17, baby);
  }

  @Override
  public boolean isCharging() {
    return this.metadata.getBoolean(18, false);
  }

  @Override
  public void setCharging(boolean charging) {
    this.metadata.setBoolean(18, charging);
  }

  @Override
  public boolean isDancing() {
    return this.metadata.getBoolean(19, false);
  }

  @Override
  public void setDancing(boolean dancing) {
    this.metadata.setBoolean(19, dancing);
  }
}
