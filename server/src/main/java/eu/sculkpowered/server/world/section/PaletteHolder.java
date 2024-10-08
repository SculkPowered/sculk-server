package eu.sculkpowered.server.world.section;

import eu.sculkpowered.server.protocol.Buffer;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;

public final class PaletteHolder implements Palette {

  private final byte dimension;
  private final byte maxBitsPerEntry;
  private final byte defaultBitsPerEntry;
  private Palette palette;

  public PaletteHolder(final int dimension, final int maxBitsPerEntry,
      final int defaultBitsPerEntry, final Palette palette) {
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
  public short size() {
    return this.palette.size();
  }

  @Override
  public void set(int x, int y, int z, int value) {
    if (this.palette instanceof SingleValuedPalette singleValued) { // not possible with single value palette
      this.palette = new DirectIndirectPalette(this.dimension, this.defaultBitsPerEntry,
          this.maxBitsPerEntry);
      this.palette.fill(singleValued.value());
    } else if (this.palette instanceof EmptyPalette) {
      this.palette = new DirectIndirectPalette(this.dimension, this.defaultBitsPerEntry,
          this.maxBitsPerEntry);
      this.palette.fill(0);
    }
    this.palette.set(x, y, z, value);
  }

  @Override
  public int get(int x, int y, int z) {
    return this.palette.get(x, y, z);
  }

  @Override
  public void fill(int value) {
    if (value == 0) {
      this.palette = Section.EMPTY_PALETTE;
    } else if (this.palette instanceof SingleValuedPalette singleValuedPalette) {
      singleValuedPalette.fill(value);
    } else {
      this.palette = new SingleValuedPalette(this.dimension, value);
    }
  }

  @Override
  public void write(Buffer buf) {
    var palette = this.palette;
    if (palette instanceof DirectIndirectPalette directIndirectPalette) {
      final var size = directIndirectPalette.size();
      if (size == 0) {
        palette = Section.EMPTY_PALETTE;
      } else {
        final var entries = new IntOpenHashSet(directIndirectPalette.paletteToValue().length);
        directIndirectPalette.getAll((x, y, z, value) -> entries.add(value));
        final var currentBitsPerEntry = directIndirectPalette.bitsPerEntry();
        final int bitsPerEntry;
        if (entries.size() == 1) {
          palette = new SingleValuedPalette(this.dimension, entries.iterator().nextInt());
        } else if (currentBitsPerEntry > this.defaultBitsPerEntry &&
            (bitsPerEntry = this.bitsToRepresent(entries.size() - 1)) < currentBitsPerEntry) {
          directIndirectPalette.resize((byte) bitsPerEntry);
        }
      }
    }
    palette.write(buf);
  }

  @Override
  public int[] paletteToValue() {
    return this.palette.paletteToValue();
  }

  @Override
  public long[] values() {
    return this.palette.values();
  }

  @FunctionalInterface
  interface Consumer {

    void accept(final int x, final int y, final int z, final int result);
  }

  public void setIndirectPalette(final int[] paletteToValue, final long[] values) {
    this.palette = new DirectIndirectPalette(this.dimension,
        this.maxBitsPerEntry, paletteToValue, values);
  }

  @Override
  public String toString() {
    return "PaletteHolder{" +
        "palette=" + this.palette +
        '}';
  }
}
