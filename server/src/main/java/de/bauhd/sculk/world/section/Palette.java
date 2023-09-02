package de.bauhd.sculk.world.section;

import de.bauhd.sculk.protocol.Buffer;

public interface Palette {

    byte dimension();

    short size();

    default short maxSize() {
        return (short) (this.dimension() * this.dimension() * this.dimension());
    }

    void set(int x, int y, int z, int value);

    int get(int x, int y, int z);

    void fill(int value);

    void write(final Buffer buf);

    default int bitsToRepresent(final int i) {
        return Integer.SIZE - Integer.numberOfLeadingZeros(i);
    }

    default int sectionIndex(int x, int y, int z) {
        final var dimensionMask = this.dimension() - 1;
        final var dimensionBitCount = this.bitsToRepresent(dimensionMask);
        return (y & dimensionMask) << (dimensionBitCount << 1) |
                (z & dimensionMask) << dimensionBitCount |
                (x & dimensionMask);
    }
}
