package de.bauhd.sculk.world.section;

import java.util.Arrays;

import static de.bauhd.sculk.util.Constants.EMPTY_BYTE_ARRAY;

public final class Section {

    static final EmptyPalette EMPTY_PALETTE = new EmptyPalette();
    private static final byte[] SKY_LIGHT = new byte[2048];

    static {
        Arrays.fill(SKY_LIGHT, (byte) -1);
    }

    private final Palette blocks;
    private final Palette biomes;
    private final byte[] skyLight;
    private final byte[] blockLight;

    public Section() {
        this(EMPTY_PALETTE, EMPTY_PALETTE, SKY_LIGHT, EMPTY_BYTE_ARRAY);
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
