package de.bauhd.minecraft.server.api.world;

public final class Section {

    private final Palette blocks;
    private final Palette biomes;
    private final byte[] skyLight;
    private final byte[] blockLight;

    public Section() {
        this.blocks = new Palette(16, 8, 4);
        this.biomes = new Palette(4, 3, 1);
        this.skyLight = new byte[0];
        this.blockLight = new byte[0];
    }

    public Palette blocks() {
        return this.blocks;
    }

    public Palette biomes() {
        return this.biomes;
    }

    public byte[] skyLight() {
        return this.skyLight;
    }

    public byte[] blockLight() {
        return this.blockLight;
    }
}
