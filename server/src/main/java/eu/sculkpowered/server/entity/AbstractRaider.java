package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;

public abstract class AbstractRaider extends AbstractMob implements Raider {

  public AbstractRaider(final SculkServer server) {
    super(server);
  }

  @Override
  public boolean celebrating() {
    return this.metadata.getBoolean(16, false);
  }

  @Override
  public void celebrating(boolean celebrating) {
    this.metadata.setBoolean(16, true);
  }
}
