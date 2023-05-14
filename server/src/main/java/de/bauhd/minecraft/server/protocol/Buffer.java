package de.bauhd.minecraft.server.protocol;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.inventory.item.ItemStack;
import de.bauhd.minecraft.server.inventory.item.Material;
import de.bauhd.minecraft.server.world.Position;
import de.bauhd.minecraft.server.protocol.packet.PacketUtils;
import de.bauhd.minecraft.server.util.Utf8;
import io.netty5.buffer.BufferInputStream;
import io.netty5.buffer.BufferOutputStream;
import io.netty5.handler.codec.EncoderException;
import net.kyori.adventure.nbt.BinaryTagIO;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.BitSet;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;

public record Buffer(io.netty5.buffer.Buffer buf) {

    private static final int STRING_CAPACITY = 65536;

    public int readUnsignedByte() {
        return this.buf.readUnsignedByte();
    }

    public @NotNull Buffer writeUnsignedByte(int value) {
        this.buf.writeUnsignedByte(value);
        return this;
    }

    public byte readByte() {
        return this.buf.readByte();
    }

    public @NotNull Buffer writeByte(byte value) {
        this.buf.writeByte(value);
        return this;
    }

    public byte[] readBytes(final int length) {
        final byte[] bytes = new byte[length];
        this.buf.readBytes(bytes, 0, length);
        return bytes;
    }

    public byte[] readAll() {
        return this.readBytes(this.buf.readableBytes());
    }

    public @NotNull Buffer writeBytes(final byte[] bytes) {
        this.buf.writeBytes(bytes);
        return this;
    }

    public short readShort() {
        return this.buf.readShort();
    }

    public @NotNull Buffer writeShort(final short value) {
        this.buf.writeShort(value);
        return this;
    }

    public int readUnsignedShort() {
        return this.buf.readUnsignedShort();
    }

    public @NotNull Buffer writeUnsignedShort(int value) {
        this.buf.writeUnsignedShort(value);
        return this;
    }

    public int readInt() {
        return this.buf.readInt();
    }

    public @NotNull Buffer writeInt(int value) {
        this.buf.writeInt(value);
        return this;
    }

    public int readVarInt() {
        return PacketUtils.readVarInt(this.buf);
    }

    public @NotNull Buffer writeVarInt(final int value) {
        PacketUtils.writeVarInt(this.buf, value);
        return this;
    }

    public float readFloat() {
        return this.buf.readFloat();
    }

    public @NotNull Buffer writeFloat(float value) {
        this.buf.writeFloat(value);
        return this;
    }

    public long readLong() {
        return this.buf.readLong();
    }

    public @NotNull Buffer writeLong(long value) {
        this.buf.writeLong(value);
        return this;
    }

    public double readDouble() {
        return this.buf.readDouble();
    }

    public @NotNull Buffer writeDouble(double value) {
        this.buf.writeDouble(value);
        return this;
    }

    public boolean readBoolean() {
        return this.buf.readBoolean();
    }

    public @NotNull Buffer writeBoolean(final boolean value) {
        this.buf.writeBoolean(value);
        return this;
    }

    public String readString() {
        return this.readString(STRING_CAPACITY);
    }

    public String readString(final int capacity) {
        return this.readString(capacity, this.readVarInt());
    }

    public String readString(final int capacity, final int length) {
        return this.buf.readCharSequence(length, UTF_8).toString();
    }

    public @NotNull Buffer writeString(String value) {
        this.writeVarInt(Utf8.encodedLength(value));
        this.buf.writeCharSequence(value, UTF_8);
        return this;
    }

    public byte[] readByteArray() {
        return this.readBytes(this.readVarInt());
    }

    public @NotNull Buffer writeByteArray(final byte[] bytes) {
        return this.writeVarInt(bytes.length).writeBytes(bytes);
    }

    public @NotNull Buffer writeComponent(final Component component) {
        return this.writeString(AdvancedMinecraftServer.getGsonSerializer(Protocol.Version.MAXIMUM_VERSION).serialize(component));
    }

    public @NotNull Buffer writeComponent(final Component component, final Protocol.Version version) {
        return this.writeString(AdvancedMinecraftServer.getGsonSerializer(version).serialize(component));
    }

    public @NotNull CompoundBinaryTag readCompoundTag() {
        try {
            return BinaryTagIO.reader().read((DataInput) new BufferInputStream(buf.send()));
        } catch (IOException e) {
            return CompoundBinaryTag.empty();
        }

    }

    public @NotNull Buffer writeCompoundTag(final CompoundBinaryTag binaryTag) {
        try {
            BinaryTagIO.writer().write(binaryTag, (DataOutput) new BufferOutputStream(buf));
        } catch (IOException e) {
            throw new EncoderException("Unable to encode NBT CompoundTag");
        }
        return this;
    }

    public UUID readUniqueId() {
        return new UUID(this.buf.readLong(), this.buf.readLong());
    }

    public @NotNull Buffer writeUniqueId(final UUID uniqueId) {
        return this.writeLong(uniqueId.getMostSignificantBits()).writeLong(uniqueId.getLeastSignificantBits());
    }

    public ItemStack readSlot() {
        if (!this.readBoolean()) {
            return null;
        }
        return new ItemStack(Material.get(this.readVarInt()), this.readByte(), this.readCompoundTag());
    }

    public @NotNull Buffer writeSlot(final @Nullable ItemStack slot) {
        if (slot != null) {
            this
                    .writeBoolean(true)
                    .writeVarInt(slot.material().ordinal())
                    .writeByte((byte) slot.amount())
                    .writeCompoundTag(slot.nbt());
        } else {
            this.buf.writeBoolean(false);
        }
        return this;
    }

    public Position readPosition() {
        final var value = this.readLong();
        return new Position(value >> 38, value << 52 >> 52, value << 26 >> 38);
    }

    public @NotNull Buffer writePosition(final @NotNull Position position) {
        return this.writePosition(position.x(), position.y(), position.z());
    }

    public @NotNull Buffer writePosition(final double x, final double y, final double z) {
        return this.writeLong((((long) x & 0x3FFFFFF) << 38) | (((long) z & 0x3FFFFFF) << 12) | ((long) y & 0xFFF));
    }

    public @NotNull Buffer writeLongArray(final long @NotNull [] longs) {
        this.writeVarInt(longs.length);
        for (final var l : longs) {
            this.writeLong(l);
        }
        return this;
    }

    public @NotNull Buffer writeBitSet(final @NotNull BitSet bitSet) {
        return this.writeLongArray(bitSet.toLongArray());
    }

    public @NotNull Buffer writeAngel(final float angel) {
        return this.writeByte((byte) (angel * 256 / 360));
    }

    public void close() {
        this.buf.close();
    }
}
