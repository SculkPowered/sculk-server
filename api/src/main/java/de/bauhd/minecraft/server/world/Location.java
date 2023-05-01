package de.bauhd.minecraft.server.world;

public class Location extends Position {

    private final World world;

    public Location(final World world, final double x, final double y, final double z) {
        super(x, y, z);
        this.world = world;
    }

    public World getWorld() {
        return this.world;
    }
}
