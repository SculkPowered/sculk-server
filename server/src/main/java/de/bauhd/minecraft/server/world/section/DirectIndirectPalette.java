package de.bauhd.minecraft.server.world.section;

import de.bauhd.minecraft.server.protocol.Buffer;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class DirectIndirectPalette implements Palette {

    private final byte dimension;
    private final byte maxBitsPerEntry;
    private byte bitsPerEntry;
    private final List<Integer> paletteToValue;
    private final Int2IntMap valueToPalette;
    private long[] values;
    private short size;

    public DirectIndirectPalette(final byte dimension, final byte bitsPerEntry, final byte maxBitsPerEntry) {
        this.dimension = dimension;
        this.maxBitsPerEntry = maxBitsPerEntry;
        this.bitsPerEntry = bitsPerEntry;
        this.paletteToValue = new ArrayList<>(1);
        this.paletteToValue.add(0);
        this.valueToPalette = new Int2IntOpenHashMap(1);
        this.valueToPalette.put(0, 0);
        this.valueToPalette.defaultReturnValue(-1);
        final int perLong = 64 / bitsPerEntry;
        this.values = new long[(this.maxSize() + perLong - 1) / perLong];
    }

    @Override
    public byte dimension() {
        return this.dimension;
    }

    @Override
    public short size() {
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
        final var clear = (1L << this.bitsPerEntry) - 1L;
        this.values[index] = old & ~(clear << bitIndex) | ((long) value << bitIndex);
        final var air = (old >> bitIndex & clear) == 0;
        if (air != (value == 0)) this.size += air ? 1 : -1;
    }

    @Override
    public int get(int x, int y, int z) {
        final var sectionIndex = this.sectionIndex(x, y, z);
        final var valuesPerLong = 64 / this.bitsPerEntry;
        final var index = sectionIndex / valuesPerLong;
        final var bitIndex = (sectionIndex - index * valuesPerLong) * this.bitsPerEntry;
        final var value = (int) (this.values[index] >> bitIndex) & ((1 << this.bitsPerEntry) - 1);
        return this.isIndirect() ? this.paletteToValue.get(value) : value;
    }

    @Override
    public void fill(int value) {
        if (value == 0) {
            Arrays.fill(this.values, 0);
            this.size = 0;
            return;
        }
        value = this.index(value);
        final var valuesPerLong = 64 / this.bitsPerEntry;
        var block = 0;
        for (var i = 0; i < valuesPerLong; i++)
            block |= (long) value << i * this.bitsPerEntry;
        Arrays.fill(this.values, block);
        this.size = this.maxSize();
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

    public byte bitsPerEntry() {
        return this.bitsPerEntry;
    }

    public List<Integer> paletteToValue() {
        return this.paletteToValue;
    }

    public void getAll(final PaletteHolder.Consumer consumer) {
        this.getAll(consumer, this.values, this.bitsPerEntry);
    }

    public void getAll(final PaletteHolder.Consumer consumer, final long[] values, final int bitsPerEntry) {
        final var magicMask = (1 << bitsPerEntry) - 1;
        final var valuesPerLong = 64 / bitsPerEntry;
        final var size = maxSize();
        final var dimensionMinus = this.dimension - 1;
        final var ids = this.isIndirect() ? this.paletteToValue.toArray() : null;
        final var dimensionBitCount = this.bitsToRepresent(dimensionMinus);
        final var shiftedDimensionBitCount = dimensionBitCount << 1;
        for (var i = 0; i < values.length; i++) {
            final var value = values[i];
            final var startIndex = i * valuesPerLong;
            final var endIndex = Math.min(startIndex + valuesPerLong, size);
            for (int index = startIndex; index < endIndex; index++) {
                final var bitIndex = (index - startIndex) * bitsPerEntry;
                final var paletteIndex = (int) (value >> bitIndex & magicMask);
                final var y = index >> shiftedDimensionBitCount;
                final var z = index >> dimensionBitCount & dimensionMinus;
                final var x = index & dimensionMinus;
                final var result = ids != null ? ids[paletteIndex] : paletteIndex;
                consumer.accept(x, y, z, (Integer) result);
            }
        }
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
        final var val = this.valueToPalette.putIfAbsent(value, lastIndex);
        if (val != -1) return val;
        // is new add
        this.paletteToValue.add(value);
        return lastIndex;
    }

    void resize(final byte bitsPerEntry) {
        final var oldValues = this.values;
        final var oldBitsPerEntry = this.bitsPerEntry;
        this.bitsPerEntry = bitsPerEntry > this.maxBitsPerEntry ? 15 : bitsPerEntry;
        final var perLong = 64 / this.bitsPerEntry;
        this.values = new long[(this.maxSize() + perLong - 1) / perLong];
        this.getAll(this::set, oldValues, oldBitsPerEntry);
    }
}
