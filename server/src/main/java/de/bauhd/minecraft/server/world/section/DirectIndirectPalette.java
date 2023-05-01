package de.bauhd.minecraft.server.world.section;

import de.bauhd.minecraft.server.protocol.Buffer;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;

import java.util.ArrayList;
import java.util.List;

public final class DirectIndirectPalette implements Palette {

    private final byte dimension;
    private final byte maxBitsPerEntry;
    private byte bitsPerEntry;
    private List<Integer> paletteToValue;
    private Int2IntMap valueToPalette;
    private long[] values;
    private int size;

    public DirectIndirectPalette(final byte dimension, final byte bitsPerEntry, final byte maxBitsPerEntry) {
        this.dimension = dimension;
        this.maxBitsPerEntry = maxBitsPerEntry;
        this.resize(bitsPerEntry);
    }

    @Override
    public byte dimension() {
        return this.dimension;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void set(int x, int y, int z, int value) {
        value = this.index(value);
        final var perLong = 64 / this.bitsPerEntry;
        final var sectionIndex = this.sectionIndex(x, y, z);
        final var index = sectionIndex / perLong;
        final int bitIndex = (sectionIndex - index * perLong) * this.bitsPerEntry;
        final var old = this.values[index];
        this.values[index] = old & ~((1L << bitsPerEntry) - 1L
                << (sectionIndex - index * perLong) * this.bitsPerEntry) | ((long) value << bitIndex);
        final var air = old == 0;
        if (air != (value == 0)) {
            this.size += air ? 1 : -1;
        }
    }

    @Override
    public void write(Buffer buf) {
        buf.writeByte(this.bitsPerEntry);
        if (this.isIndirect()) { // indirect
            buf.writeVarInt(this.paletteToValue.size());
            for (final var i : this.paletteToValue) {
                buf.writeVarInt(i);
            }
        } // direct
        buf.writeLongArray(this.values);
    }

    private boolean isIndirect() {
        return this.bitsPerEntry <= this.maxBitsPerEntry;
    }

    private int index(final int value) {
        if (!this.isIndirect()) {
            return value;
        }
        final var lastIndex = this.paletteToValue.size();
        if (lastIndex >= 1 << this.bitsPerEntry) { // is full
            this.resize((byte) (this.bitsPerEntry + 1));
            return this.index(value); // try again, after resize
        }
        final int val = this.valueToPalette.putIfAbsent(value, lastIndex);
        if (val != -1) {
            return val;
        }
        // is new add
        this.paletteToValue.add(value);
        return lastIndex;
    }

    private void resize(final byte bitsPerEntry) {
        this.bitsPerEntry = (bitsPerEntry > this.maxBitsPerEntry ? 15 : bitsPerEntry);
        this.paletteToValue = new ArrayList<>(1);
        this.paletteToValue.add(0);
        this.valueToPalette = new Int2IntOpenHashMap(1);
        this.valueToPalette.put(0, 0);
        this.valueToPalette.defaultReturnValue(-1);

        final var perLong = 64 / bitsPerEntry;
        this.values = new long[(this.maxSize() + perLong - 1) / perLong];
    }
}
