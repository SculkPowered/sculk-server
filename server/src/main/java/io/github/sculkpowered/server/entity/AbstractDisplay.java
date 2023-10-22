package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractDisplay extends AbstractEntity implements Display {

  @Override
  public int getInterpolationDelay() {
    return this.metadata.getVarInt(8, 0);
  }

  @Override
  public void setInterpolationDelay(int interpolationDelay) {
    this.metadata.setVarInt(8, interpolationDelay);
  }

  @Override
  public int getInterpolationDuration() {
    return this.metadata.getVarInt(9, 0);
  }

  @Override
  public void setInterpolationDuration(int interpolationDuration) {
    this.metadata.setVarInt(9, interpolationDuration);
  }

  @Override
  public @NotNull Display.Constraint getConstraint() {
    return this.metadata.getEnum(14, Display.Constraint.FIXED);
  }

  @Override
  public void setConstraint(@NotNull Display.Constraint constraint) {
    this.metadata.setByte(14, (byte) constraint.ordinal());
  }

  @Override
  public float getViewRange() {
    return this.metadata.getFloat(16, 1);
  }

  @Override
  public void setViewRange(float viewRange) {
    this.metadata.setFloat(16, viewRange);
  }

  @Override
  public float getShadowRange() {
    return this.metadata.getFloat(17, 0);
  }

  @Override
  public void setShadowRange(float shadowRange) {
    this.metadata.setFloat(17, shadowRange);
  }

  @Override
  public float getShadowStrength() {
    return this.metadata.getFloat(18, 1);
  }

  @Override
  public void setShadowStrength(float shadowStrength) {
    this.metadata.setFloat(17, shadowStrength);
  }

  @Override
  public float getWidth() {
    return this.metadata.getFloat(19, 0);
  }

  @Override
  public void setWidth(float width) {
    this.metadata.setFloat(19, width);
  }

  @Override
  public float getHeight() {
    return this.metadata.getFloat(20, 0);
  }

  @Override
  public void setHeight(float height) {
    this.metadata.setFloat(20, height);
  }
}
