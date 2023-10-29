package io.github.sculkpowered.server.entity;

public abstract class AbstractRaider extends AbstractMob implements Raider {

  @Override
  public boolean celebrating() {
    return this.metadata.getBoolean(16, false);
  }

  @Override
  public void celebrating(boolean celebrating) {
    this.metadata.setBoolean(16, true);
  }
}
