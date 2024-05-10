package io.github.sculkpowered.server.container.item.data;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.Buffer.Reader;
import io.github.sculkpowered.server.protocol.Buffer.Writer;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.intellij.lang.annotations.Subst;
import org.jetbrains.annotations.NotNull;

public final class SculkDataComponentType<T> implements DataComponentType<T> {

  private final Key key;
  private final int id;
  private final Writer<T> writer;
  private final Reader<T> reader;
  private final BinarySerializer<T> serializer;

  public SculkDataComponentType(
      final @Subst("value") String keyValue,
      final int id,
      final Writer<T> writer,
      final Reader<T> reader,
      final BinarySerializer<T> serializer
  ) {
    this.key = Key.key(Key.MINECRAFT_NAMESPACE, keyValue);
    this.id = id;
    this.writer = writer;
    this.reader = reader;
    this.serializer = serializer;
  }

  @Override
  public @NotNull Key key() {
    return this.key;
  }

  @Override
  public int id() {
    return this.id;
  }

  @Override
  public @NotNull CompoundBinaryTag asNBT() {
    return CompoundBinaryTag.empty();
  }

  @Override
  public String toString() {
    return "SculkDataComponentType{" +
        "key=" + this.key +
        ", id=" + this.id +
        '}';
  }

  @Override
  public boolean serializable() {
    return this.serializer != null;
  }

  @Override
  public BinaryTag valueToBinary(@NotNull T value) {
    return this.serializer.serialize(value);
  }

  @Override
  public T binaryToValue(@NotNull BinaryTag binaryTag) {
    return this.serializer.deserialize(binaryTag);
  }

  public boolean writable() {
    return this.writer != null && this.reader != null;
  }

  public void write(final Buffer buf, final T value) {
    this.writer.write(buf, value);
  }

  public T read(final Buffer buf) {
    return this.reader.read(buf);
  }
}