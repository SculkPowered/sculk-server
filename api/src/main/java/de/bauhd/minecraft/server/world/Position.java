package de.bauhd.minecraft.server.world;

public class Position {

    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;

    public Position(final double x, final double y, final double z) {
        this(x, y, z, 0F, 0F);
    }

    public Position(final double x, final double y, final double z, final float yaw, final float pitch) {
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

    public Position setX(final double x) {
        return new Position(x, this.y, this.z, this.yaw, this.pitch);
    }
    public Position setY(final double y) {
        return new Position(this.x, y, this.z, this.yaw, this.pitch);
    }

    public Position setZ(final double z) {
        return new Position(this.x, this.y, z, this.yaw, this.pitch);
    }

    public Position setYaw(final float yaw) {
        return new Position(this.x, this.y, this.z, yaw, this.pitch);
    }

    public Position setPitch(final float pitch) {
        return new Position(this.x, this.y, this.z, this.yaw, pitch);
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
}
