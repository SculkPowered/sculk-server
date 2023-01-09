package de.bauhd.minecraft.server.api.world.section;

import de.bauhd.minecraft.server.protocol.Buffer;

public interface Palette {

    byte dimension();

    int size();

    default int maxSize() {
        return this.dimension() * this.dimension() * this.dimension();
    }

    void set(int x, int y, int z, int value);

    void write(final Buffer buf);

    default int sectionIndex(int x, int y, int z) {
        final var dimensionMask = this.dimension() - 1;
        final var dimensionBitCount = Integer.SIZE - Integer.numberOfLeadingZeros(dimensionMask);
        return (y & dimensionMask) << (dimensionBitCount << 1) |
                (z & dimensionMask) << dimensionBitCount |
                (x & dimensionMask);
    }

}
