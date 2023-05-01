package de.bauhd.minecraft.server.world.section;

public final class Section {

    private static final Palette EMPTY_16 = new SingleValuedPalette(16, 0);
    private static final Palette EMPTY_4 = new SingleValuedPalette(4, 0);

    private final Palette blocks;
    private final Palette biomes;
    private final byte[] skyLight;
    private final byte[] blockLight;

    public Section() {
        this.blocks = new PaletteHolder(16, 8, 4, EMPTY_16);
        this.biomes = new PaletteHolder(4, 3, 1, EMPTY_4);
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
