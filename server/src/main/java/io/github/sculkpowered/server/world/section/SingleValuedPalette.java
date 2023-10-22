package io.github.sculkpowered.server.world.section;

import io.github.sculkpowered.server.protocol.Buffer;

final class SingleValuedPalette implements Palette {

  private final byte dimension;
  private int value;

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
  public short size() {
    return this.value != 0 ? this.maxSize() : 0;
  }

  @Override
  public void set(int x, int y, int z, int value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int get(int x, int y, int z) {
    return this.value;
  }

  @Override
  public void fill(int value) {
    this.value = value;
  }

  @Override
  public void write(Buffer buf) {
    buf
        .writeByte((byte) 0)
        .writeVarInt(this.value)
        .writeVarInt(0);
  }

  @Override
  public int[] paletteToValue() {
    return new int[this.value];
  }

  @Override
  public long[] values() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String toString() {
    return "SingleValuedPalette{" +
        "dimension=" + this.dimension +
        ", value=" + this.value +
        '}';
  }
}
