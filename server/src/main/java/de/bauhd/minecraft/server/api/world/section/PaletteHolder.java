package de.bauhd.minecraft.server.api.world.section;

import de.bauhd.minecraft.server.protocol.Buffer;

public final class PaletteHolder implements Palette {

    private final byte dimension;
    private final byte maxBitsPerEntry;
    private final byte defaultBitsPerEntry;
    private Palette palette;

    public PaletteHolder(final int dimension, final int maxBitsPerEntry, final int defaultBitsPerEntry, final Palette palette) {
        this.dimension = (byte) dimension;
        this.maxBitsPerEntry = (byte) maxBitsPerEntry;
        this.defaultBitsPerEntry = (byte) defaultBitsPerEntry;
        this.palette = palette;
    }

    @Override
    public byte dimension() {
        return this.dimension;
    }

    @Override
    public int size() {
        return this.palette.size();
    }

    @Override
    public void set(int x, int y, int z, int value) {
        final var palette = this.palette;
        if (palette instanceof SingleValuedPalette) { // not possible with single value palette
            this.palette = new DirectIndirectPalette(this.dimension, this.defaultBitsPerEntry, this.maxBitsPerEntry);
        }
        this.palette.set(x, y, z, value);
    }

    @Override
    public void write(Buffer buf) {
        this.palette.write(buf);
    }
}
