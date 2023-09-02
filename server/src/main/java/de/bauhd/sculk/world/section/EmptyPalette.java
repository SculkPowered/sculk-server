package de.bauhd.sculk.world.section;

import de.bauhd.sculk.protocol.Buffer;

public final class EmptyPalette implements Palette {

    @Override
    public byte dimension() {
        throw new UnsupportedOperationException();
    }

    @Override
    public short size() {
        return 0;
    }

    @Override
    public void set(int x, int y, int z, int value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int get(int x, int y, int z) {
        return 0;
    }

    @Override
    public void fill(int value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(Buffer buf) {
        buf.writeByte((byte) 0).writeByte((byte) 0).writeByte((byte) 0);
    }
}
