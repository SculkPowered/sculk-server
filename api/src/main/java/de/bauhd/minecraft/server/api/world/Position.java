package de.bauhd.minecraft.server.api.world;

public class Position {

    private final double x;
    private final double y;
    private final double z;

    public Position(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }
}
