package io.github.sculkpowered.server.util;

import static io.github.sculkpowered.server.world.chunk.Chunk.CHUNK_SIZE_X;
import static io.github.sculkpowered.server.world.chunk.Chunk.CHUNK_SIZE_Z;

public final class CoordinateUtil {

  public static int chunkCoordinate(final double coordinate) {
    return chunkCoordinate((int) coordinate);
  }

  public static int chunkCoordinate(final int coordinate) {
    return coordinate >> 4;
  }

  public static long chunkIndex(final int x, final int z) {
    return ((((long) x) << 32) | (z & 0xFFFFFFFFL));
  }

  public static int relativeCoordinate(final int coordinate) {
    return coordinate & 0xF;
  }

  public static int regionCoordinate(final int coordinate) {
    return (int) Math.floor((double) coordinate / 32);
  }

  public static int blockIndex(int x, int y, int z) {
    x = x % CHUNK_SIZE_X;
    z = z % CHUNK_SIZE_Z;
    var index = x & 0xF;
    if (y > 0) {
      index |= (y << 4) & 0x07FFFFF0;
    } else {
      index |= ((-y) << 4) & 0x7FFFFF0;
      index |= 1 << 27;
    }
    index |= (z << 28) & 0xF0000000;
    return index;
  }

  public static int chunkPositionXFromBlockIndex(final int index) {
    return index & 0xF;
  }

  public static int chunkPositionYFromBlockIndex(final int index) {
    var y = (index & 0x07FFFFF0) >>> 4;
    if (((index >>> 27) & 1) == 1) {
      y = -y;
    }
    return y;
  }

  public static int chunkPositionZFromBlockIndex(final int index) {
    return (index >> 28) & 0xF;
  }

  public static void forChunksInRange(
      final int chunkX, final int chunkZ, final int range, final ChunkConsumer chunk
  ) {
    for (var x = -range; x <= range; x++) {
      for (var z = -range; z <= range; z++) {
        chunk.accept(chunkX + x, chunkZ + z);
      }
    }
  }

  @FunctionalInterface
  public interface ChunkConsumer {

    void accept(int chunkX, int chunkZ);
  }
}
