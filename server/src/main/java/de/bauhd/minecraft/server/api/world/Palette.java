package de.bauhd.minecraft.server.api.world;

public final class Palette {

    private final byte dimension;
    private final byte maxBitsPerEntry;
    private final byte defaultBitsPerEntry;
    private int value;

    public Palette(final int dimension, final int maxBitsPerEntry, final int defaultBitsPerEntry) {
        this.dimension = (byte) dimension;
        this.maxBitsPerEntry = (byte) maxBitsPerEntry;
        this.defaultBitsPerEntry = (byte) defaultBitsPerEntry;
    }

    public int size() {
        return this.value != 0 ? this.dimension * this.dimension * this.dimension : 0;
    }

    public void set(final int x, final int y, final int z, final int stateId) {
        this.relevantCoordinate(x);
    }

    private int relevantCoordinate(final int coordinate) {
        return coordinate & 0xF;
    }
}
