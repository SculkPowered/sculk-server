package de.bauhd.minecraft.server.world.section;

public final class Section {

    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    private final Palette blocks;
    private final Palette biomes;
    private final byte[] skyLight;
    private final byte[] blockLight;

    public Section() {
        this(true);
    }

    public Section(final boolean init) {
        this((init ? new SingleValuedPalette(16, 0) : null),
                (init ? new SingleValuedPalette(4, 0) : null));
    }

    private Section(final Palette blocks, final Palette biomes) {
        this.blocks = new PaletteHolder(16, 8, 4, blocks);
        this.biomes = new PaletteHolder(4, 3, 1, biomes);
        this.skyLight = EMPTY_BYTE_ARRAY;
        this.blockLight = EMPTY_BYTE_ARRAY;
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
