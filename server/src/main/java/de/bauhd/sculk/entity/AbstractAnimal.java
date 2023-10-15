package de.bauhd.sculk.entity;

public abstract class AbstractAnimal extends AbstractMob implements Animal {

  @Override
  public boolean isBaby() {
    return this.metadata.getBoolean(16, false);
  }

  @Override
  public void setBaby() {
    this.setBaby(true);
  }

  @Override
  public boolean isAdult() {
    return !this.isBaby();
  }

  @Override
  public void setAdult() {
    this.setBaby(false);
  }

  public void setBaby(boolean baby) {
    this.metadata.setBoolean(16, baby);
  }
}
