package io.github.sculkpowered.server.world;

import org.jetbrains.annotations.NotNull;

public final class Vector {

  private static final Vector ZERO = new Vector(0D, 0D, 0D);

  private double x;
  private double y;
  private double z;

  public Vector(final double x, final double y, final double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public double x() {
    return this.x;
  }

  public double y() {
    return this.y;
  }

  public double z() {
    return this.z;
  }

  public @NotNull Vector multiply(final double multiplier) {
    this.x *= multiplier;
    this.y *= multiplier;
    this.z *= multiplier;
    return this;
  }

  public @NotNull Vector multiply(final Vector vector) {
    this.x *= vector.x;
    this.y *= vector.y;
    this.z *= vector.z;
    return this;
  }

  public @NotNull Vector normalize() {
    final var length = this.length();
    this.x /= length;
    this.y /= length;
    this.z /= length;
    return this;
  }

  public double length() {
    return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
  }

  public static Vector zero() {
    return ZERO;
  }

  @Override
  public String toString() {
    return "Vector{" +
        "x=" + this.x +
        ", y=" + this.y +
        ", z=" + this.z +
        '}';
  }
}
