package de.bauhd.sculk.entity;

public abstract class AbstractMob extends AbstractLivingEntity implements Mob {

  @Override
  public boolean isAggressive() {
    return this.metadata.inMask(15, 0x04);
  }
}
