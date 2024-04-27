package io.github.sculkpowered.server.container.item.data;

import io.github.sculkpowered.server.protocol.Buffer;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.intellij.lang.annotations.Subst;
import org.jetbrains.annotations.NotNull;

public abstract class SculkDataComponentType<T> implements DataComponentType<T> {

  private final Key key;
  private final int id;

  public SculkDataComponentType(final @Subst("value") String keyValue, final int id) {
    this.key = Key.key(Key.MINECRAFT_NAMESPACE, keyValue);
    this.id = id;
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

  public abstract void write(final Buffer buf, final T value);

  public abstract T read(final Buffer buf);
}