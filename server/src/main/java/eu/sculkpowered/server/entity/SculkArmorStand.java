package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkArmorStand extends AbstractLivingEntity implements ArmorStand {

  public SculkArmorStand(final SculkServer server) {
    super(server);
  }

  @Override
  public boolean small() {
    return this.inMask(0x01);
  }

  @Override
  public void setSmall() {
    this.setSmall(true);
  }

  @Override
  public boolean big() {
    return !this.small();
  }

  @Override
  public void setBig() {
    this.setSmall(false);
  }

  public void setSmall(boolean small) {
    this.setMask(0x01, small);
  }

  @Override
  public boolean hasArms() {
    return this.inMask(0x04);
  }

  @Override
  public void arms(boolean arms) {
    this.setMask(0x04, arms);
  }

  @Override
  public boolean hasBasePlate() {
    return !this.inMask(0x08);
  }

  @Override
  public void basePlate(boolean basePlate) {
    this.setMask(0x08, !basePlate);
  }

  @Override
  public boolean marker() {
    return this.inMask(0x10);
  }

  @Override
  public void marker(boolean marker) {
    this.setMask(0x10, marker);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.ARMOR_STAND;
  }

  private boolean inMask(final int value) {
    return (this.metadata.getByte(15, (byte) 0) & value) == value;
  }

  private void setMask(int i, boolean value) {
    var mask = this.metadata.getByte(15, (byte) 0);
    if (((mask & i) == i) == value) {
      return;
    }
    if (value) {
      mask |= 0x20;
    } else {
      mask &= ~0x20;
    }
    this.metadata.setByte(15, mask);
  }
}
