package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;

public abstract class AbstractAnimal extends AbstractMob implements Animal {

  public AbstractAnimal(final SculkServer server) {
    super(server);
  }

  @Override
  public boolean baby() {
    return this.metadata.getBoolean(16, false);
  }

  @Override
  public boolean adult() {
    return !this.baby();
  }

  @Override
  public void setBaby(boolean baby) {
    this.metadata.setBoolean(16, baby);
  }
}
