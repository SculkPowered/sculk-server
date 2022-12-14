package de.bauhd.minecraft.server.protocol.packet;

import io.netty5.buffer.Buffer;

public final class PacketUtils {

    public static void writeVarInt(final Buffer buf, int input) {
        while ((input & -128) != 0) {
            buf.writeByte((byte) (input & 127 | 128));
            input >>>= 7;
        }
        buf.writeByte((byte) input);
    }

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
