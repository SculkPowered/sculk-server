package io.github.sculkpowered.server.entity;

public abstract class AbstractAnimal extends AbstractMob implements Animal {

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
