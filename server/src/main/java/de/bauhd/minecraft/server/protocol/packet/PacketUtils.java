package de.bauhd.minecraft.server.protocol.packet;

import de.bauhd.minecraft.server.api.inventory.Slot;
import de.bauhd.minecraft.server.util.Utf8;
import io.netty5.buffer.Buffer;
import io.netty5.buffer.BufferOutputStream;
import io.netty5.handler.codec.EncoderException;
import net.kyori.adventure.nbt.BinaryTagIO;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;

import java.io.DataOutput;
import java.io.IOException;
import java.util.BitSet;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;

public final class PacketUtils {

    public static void writeString(final Buffer buf, final @NotNull CharSequence charSequence) {
        writeVarInt(buf, Utf8.encodedLength(charSequence));
        buf.writeCharSequence(charSequence, UTF_8);
    }

    public static String readString(final Buffer buf) {
        return readString(buf, 65536);
    }

    public static String readString(final Buffer buf, final int cap) {
        return readString(buf, cap, readVarInt(buf));
    }

    private static String readString(final Buffer buf, final int cap, final int length) {
        return buf.readCharSequence(length, UTF_8).toString();
    }

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

    public static void writeCompoundTag(final Buffer buf, final CompoundBinaryTag compoundTag) {
        try {
            BinaryTagIO.writer().write(compoundTag, (DataOutput) new BufferOutputStream(buf));
        } catch (IOException e) {
            throw new EncoderException("Unable to encode NBT CompoundTag");
        }
    }

    public static void writeUUID(final Buffer buf, final UUID uuid) {
        buf.writeLong(uuid.getMostSignificantBits());
        buf.writeLong(uuid.getLeastSignificantBits());
    }

    public static UUID readUUID(final Buffer buf) {
        return new UUID(buf.readLong(), buf.readLong());
    }

    public static void writeByteArray(final Buffer buf, final byte[] bytes) {
        writeVarInt(buf, bytes.length);
        buf.writeBytes(bytes);
    }

    public static byte[] readByteArray(final Buffer buf) {
        final var length = readVarInt(buf);
        final var bytes = new byte[length];
        buf.readBytes(bytes, 0, length);
        return bytes;
    }

    public static void writeBitSet(final Buffer buf, final BitSet bitSet) {
        final var longs = bitSet.toLongArray();
        writeVarInt(buf, longs.length);
        for (final var l : longs) {
            buf.writeLong(l);
        }
    }

    public static void writeAngle(final Buffer buf, final float f) {
        buf.writeByte((byte) (f * 256 / 360));
    }

    public static void writeSlot(final Buffer buf, final Slot slot) {
        if (slot != null) {
            buf.writeBoolean(true);
            writeVarInt(buf, slot.material().ordinal());
            buf.writeByte((byte) slot.count());
            writeCompoundTag(buf, CompoundBinaryTag.empty());
        } else {
            buf.writeBoolean(false);
        }
    }
}
