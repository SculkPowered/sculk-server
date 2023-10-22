package io.github.sculkpowered.server.world.section;

import io.github.sculkpowered.server.protocol.Buffer;

final class EmptyPalette implements Palette {

  private static final int[] EMPTY_VALUES = new int[1];

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

  @Override
  public int[] paletteToValue() {
    return EMPTY_VALUES;
  }

  @Override
  public long[] values() {
    throw new UnsupportedOperationException();
  }
}
