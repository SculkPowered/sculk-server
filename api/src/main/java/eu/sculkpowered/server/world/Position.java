package eu.sculkpowered.server.world;

import org.jetbrains.annotations.NotNull;

/**
 * Represent a position.
 */
public class Position {

  private static final Position ZERO = Position.position(0, 0, 0);

  private final double x;
  private final double y;
  private final double z;
  private final float yaw;
  private final float pitch;

  private Position(
      final double x,
      final double y,
      final double z,
      final float yaw,
      final float pitch
  ) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.yaw = yaw;
    this.pitch = pitch;
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

  public float yaw() {
    return this.yaw;
  }

  public float pitch() {
    return this.pitch;
  }

  public Position add(final double x, final double y, final double z) {
    return new Position(this.x + x, this.y + y, this.z + z, this.yaw, this.pitch);
  }

  public Position subtract(final double x, final double y, final double z) {
    return new Position(this.x - x, this.y - y, this.z - z, this.yaw, this.pitch);
  }

  public Position x(final double x) {
    return new Position(x, this.y, this.z, this.yaw, this.pitch);
  }

  public Position y(final double y) {
    return new Position(this.x, y, this.z, this.yaw, this.pitch);
  }

  public Position z(final double z) {
    return new Position(this.x, this.y, z, this.yaw, this.pitch);
  }

  public Position yaw(final float yaw) {
    return new Position(this.x, this.y, this.z, yaw, this.pitch);
  }

  public Position pitch(final float pitch) {
    return new Position(this.x, this.y, this.z, this.yaw, pitch);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Position other)) {
      return false;
    }
    return (other.x == this.x
        && other.y == this.y
        && other.z == this.z
        && other.yaw == this.yaw
        && other.pitch == this.pitch);
  }

  public boolean pointEquals(@NotNull Position position) {
    return ((int) position.x == (int) this.x
        && (int) position.y == (int) this.y
        && (int) position.z == (int) this.z);
  }

  @Override
  public String toString() {
    return "Position{" +
        "x=" + this.x +
        ", y=" + this.y +
        ", z=" + this.z +
        ", yaw=" + this.yaw +
        ", pitch=" + this.pitch +
        '}';
  }

  public static @NotNull Position position(final double x, final double y, final double z) {
    return Position.position(x, y, z, 0F, 0F);
  }

  public static @NotNull Position position(
      final double x,
      final double y,
      final double z,
      final float yaw,
      final float pitch
  ) {
    return new Position(x, y, z, yaw, pitch);
  }

  public static @NotNull Position zero() {
    return ZERO;
  }
}
