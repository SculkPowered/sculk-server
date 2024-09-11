package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkPillager extends AbstractRaider implements Pillager {

  public SculkPillager(final SculkServer server) {
    super(server);
  }

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
