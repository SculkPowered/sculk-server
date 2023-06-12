package de.bauhd.minecraft.server.protocol.packet;

import io.netty5.buffer.Buffer;

public final class PacketUtils {

    private static final byte[] VARINT_EXACT_BYTE_LENGTHS =
            {5, 5, 5, 5, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1};

    public static int varIntLength(final int value) {
        return VARINT_EXACT_BYTE_LENGTHS[Integer.numberOfLeadingZeros(value)];
    }

    // https://steinborn.me/posts/performance/how-fast-can-you-write-a-varint/
    public static void writeVarInt(final Buffer buf, int value) {
        if ((value & (0xFFFFFFFF << 7)) == 0) {
            buf.writeByte((byte) value);
        } else if ((value & (0xFFFFFFFF << 14)) == 0) {
            buf.writeShort((short) ((value & 0x7F | 0x80) << 8 | (value >>> 7)));
        } else if ((value & (0xFFFFFFFF << 21)) == 0) {
            buf.writeMedium((value & 0x7F | 0x80) << 16 | ((value >>> 7) & 0x7F | 0x80) << 8 | (value >>> 14));
        } else if ((value & (0xFFFFFFFF << 28)) == 0) {
            buf.writeInt((value & 0x7F | 0x80) << 24 | (((value >>> 7) & 0x7F | 0x80) << 16)
                    | ((value >>> 14) & 0x7F | 0x80) << 8 | (value >>> 21));
        } else {
            buf.writeInt((value & 0x7F | 0x80) << 24 | ((value >>> 7) & 0x7F | 0x80) << 16
                    | ((value >>> 14) & 0x7F | 0x80) << 8 | ((value >>> 21) & 0x7F | 0x80));
            buf.writeByte((byte) (value >>> 28));
        }}

    public static int readVarInt(final Buffer buf) {
        var value = 0;
        var position = 0;

        while (true) {
            final var current = buf.readByte();
            value |= (current & 127) << position++ * 7;

            if (position > 5) {
                throw new RuntimeException("VarInt too big");
            }
            if ((current & 128) != 128) {
                break;
            }
        }
        return value;
    }

}
