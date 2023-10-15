package de.bauhd.sculk.entity;

public abstract class AbstractRaider extends AbstractMob implements Raider {

  @Override
  public boolean isCelebrating() {
    return this.metadata.getBoolean(16, false);
  }

  @Override
  public void setCelebrating(boolean celebrating) {
    this.metadata.setBoolean(16, true);
  }
}
