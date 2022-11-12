package de.bauhd.minecraft.server.api.world;

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
}
