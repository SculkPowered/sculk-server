package de.bauhd.sculk.util;

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
}
