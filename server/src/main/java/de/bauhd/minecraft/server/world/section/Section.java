package de.bauhd.minecraft.server.world.section;

public final class Section {

    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    private final Palette blocks;
    private final Palette biomes;
    private final byte[] skyLight;
    private final byte[] blockLight;

    public Section() {
        this(new SingleValuedPalette(16, 0), new SingleValuedPalette(4, 0),
                EMPTY_BYTE_ARRAY, EMPTY_BYTE_ARRAY);
    }

    public Section(final byte[] skyLight, final byte[] blockLight) {
        this(null, null, skyLight, blockLight);
    }

    private Section(final Palette blocks, final Palette biomes, final byte[] skyLight, final byte[] blockLight) {
        this.blocks = new PaletteHolder(16, 8, 4, blocks);
        this.biomes = new PaletteHolder(4, 3, 1, biomes);
        this.skyLight = skyLight;
        this.blockLight = blockLight;
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
