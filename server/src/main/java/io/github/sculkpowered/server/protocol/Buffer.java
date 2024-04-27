package io.github.sculkpowered.server.protocol;

import static java.nio.charset.StandardCharsets.UTF_8;

import io.github.sculkpowered.server.container.item.ItemStack;
import io.github.sculkpowered.server.container.item.Material;
import io.github.sculkpowered.server.container.item.data.DataComponentType;
import io.github.sculkpowered.server.container.item.data.SculkDataComponentType;
import io.github.sculkpowered.server.protocol.packet.PacketUtils;
import io.github.sculkpowered.server.registry.Registries;
import io.github.sculkpowered.server.util.Utf8;
import io.github.sculkpowered.server.world.Position;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import java.io.IOException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.BinaryTagType;
import net.kyori.adventure.nbt.BinaryTagTypes;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Buffer {

  private static final int STRING_CAPACITY = 65536;
  private static final GsonComponentSerializer PRE_1_16_SERIALIZER = GsonComponentSerializer.colorDownsamplingGson();
  private static final GsonComponentSerializer MODERN_SERIALIZER = GsonComponentSerializer.gson();

  private final ByteBuf buf;

  public Buffer(final ByteBuf buf) {
    this.buf = buf;
  }

  public int readUnsignedByte() {
    return this.buf.readUnsignedByte();
  }

  public @NotNull Buffer writeUnsignedByte(int value) {
    this.buf.writeByte(value);
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

  public @NotNull Buffer writeVarLong(long value) {
    while (true) {
      if ((value & ~((long) 0x7F)) == 0) {
        this.writeByte((byte) value);
        return this;
      }
      this.writeByte((byte) ((value & 0x7F) | 0x80));
      value >>>= 7;
    }
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
    if (capacity < length) {
      throw new AssertionError(
          "Length should be lower than capacity. (" + capacity + " <" + length + ")");
    }
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

  public @NotNull Buffer writeComponent(final @NotNull Component component) {
    return this.writeBinaryTag(
        ComponentSerializer.serializeToNbt(component)); // TODO: replace with adventures one
  }

  public @NotNull Buffer writeComponentJson(final @NotNull Component component) {
    return this.writeString(MODERN_SERIALIZER.serialize(component));
  }

  public @NotNull CompoundBinaryTag readCompoundTag() {
    try (final var inputStream = new ByteBufInputStream(this.buf)) {
      final var type = inputStream.readByte();
      if (type == BinaryTagTypes.COMPOUND.id()) {
        return BinaryTagTypes.COMPOUND.read(inputStream);
      } else if (type == BinaryTagTypes.END.id()) {
        return CompoundBinaryTag.empty();
      } else {
        throw new AssertionError();
      }
    } catch (IOException e) {
      throw new DecoderException("Unable to decode compound tag: " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  public <T extends BinaryTag> @NotNull Buffer writeBinaryTag(final T binaryTag) {
    try (final var outputStream = new ByteBufOutputStream(this.buf)) {
      final var type = (BinaryTagType<T>) binaryTag.type();
      outputStream.write(type.id());
      type.write(binaryTag, outputStream);
    } catch (IOException e) {
      throw new EncoderException("Unable to encode binary tag: " + e.getMessage());
    }
    return this;
  }

  public UUID readUniqueId() {
    return new UUID(this.buf.readLong(), this.buf.readLong());
  }

  public @NotNull Buffer writeUniqueId(final UUID uniqueId) {
    return this.writeLong(uniqueId.getMostSignificantBits())
        .writeLong(uniqueId.getLeastSignificantBits());
  }

  public @NotNull ItemStack readItem() {
    final var amount = this.readVarInt();
    if (amount == 0) {
      return ItemStack.empty();
    }
    return ItemStack.itemStack(Material.get(this.readVarInt()), amount, this.readDataComponents());
  }

  public @NotNull Buffer writeItem(final @NotNull ItemStack item) {
    if (!item.isEmpty()) {
      this
          .writeVarInt(item.amount())
          .writeVarInt(item.material().id())
          .writeDataComponents(item.components().components());
    } else {
      this.writeVarInt(0);
    }
    return this;
  }

  public Position readPosition() {
    final var value = this.readLong();
    return Position.position(value >> 38, value << 52 >> 52, value << 26 >> 38);
  }

  public @NotNull Buffer writePosition(final @NotNull Position position) {
    return this.writePosition(position.x(), position.y(), position.z());
  }

  public @NotNull Buffer writePosition(final double x, final double y, final double z) {
    return this.writeLong(
        (((long) x & 0x3FFFFFF) << 38) | (((long) z & 0x3FFFFFF) << 12) | ((long) y & 0xFFF));
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

  public boolean writeOptional(final @Nullable Object object) {
    var b = object != null;
    this.buf.writeBoolean(b);
    return b;
  }

  public <T extends Enum<T>> T readEnum(final Class<T> enumClass) {
    return enumClass.getEnumConstants()[this.readVarInt()];
  }

  private @NotNull Map<DataComponentType<?>, Optional<?>> readDataComponents() {
    final var components = this.readVarInt();
    final var removedComponents = this.readVarInt();
    if (components == 0 && removedComponents == 0) {
      return Map.of();
    } else {
      final var map = new HashMap<DataComponentType<?>, Optional<?>>(
          components + removedComponents);
      for (var i = 0; i < components; i++) {
        final var type = (SculkDataComponentType<?>) Registries.dataComponentTypes().get(this.readVarInt());
        map.put(type, Optional.of(type.read(this)));
      }

      for (var i = 0; i < removedComponents; i++) {
        map.put(Registries.dataComponentTypes().get(this.readVarInt()), Optional.empty());
      }
      return map;
    }
  }

  public @NotNull Buffer writeDataComponents(
      final @NotNull Map<DataComponentType<?>, Optional<?>> map) {
    if (map.isEmpty()) {
      return this
          .writeVarInt(0)
          .writeVarInt(0);
    }
    var present = 0;
    var empty = 0;
    // TODO: optimize iterations here
    for (final var entry : map.entrySet()) {
      if (entry.getValue().isPresent()) {
        present++;
      } else {
        empty++;
      }
    }
    this
        .writeVarInt(present)
        .writeVarInt(empty);
    for (final var entry : map.entrySet()) {
      if (entry.getValue().isPresent()) {
        @SuppressWarnings("unchecked") final var key = (SculkDataComponentType<Object>) entry.getKey();
        this.writeVarInt(key.id());
        key.write(this, entry.getValue().get());
      }
    }
    for (final var entry : map.entrySet()) {
      if (entry.getValue().isEmpty()) {
        this.writeVarInt(entry.getKey().id());
      }
    }
    return this;
  }

  public int readableBytes() {
    return this.buf.readableBytes();
  }

  public void close() {
    this.buf.release();
  }

  public static GsonComponentSerializer getGsonSerializer(final int version) {
    return version >= 735 ? MODERN_SERIALIZER : PRE_1_16_SERIALIZER;
  }
}
