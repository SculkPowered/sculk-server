package de.bauhd.minecraft.server.api.world.section;

import de.bauhd.minecraft.server.protocol.Buffer;

public final class SingleValuedPalette implements Palette {

    private final byte dimension;
    private final int value;

    public SingleValuedPalette(final int dimension, final int value) {
        this.dimension = (byte) dimension;
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    @Override
    public byte dimension() {
        return this.dimension;
    }

    @Override
    public int size() {
        return this.value != 0 ? this.maxSize() : 0;
    }

    @Override
    public void set(int x, int y, int z, int value) {

    }

    @Override
    public void write(Buffer buf) {
        buf
                .writeByte((byte) 0)
                .writeVarInt(this.value)
                .writeVarInt(0);
    }

    @Override
    public String toString() {
        return "SingleValuedPalette{" +
                "dimension=" + this.dimension +
                ", value=" + this.value +
                '}';
    }
}
