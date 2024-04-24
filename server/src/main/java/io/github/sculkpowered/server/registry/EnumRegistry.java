package io.github.sculkpowered.server.registry;

import java.util.Collection;
import java.util.List;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;

public final class EnumRegistry<T extends Registry.Entry> implements Registry<T> {

  private final String type;
  private final T defaultValue;
  private final List<T> entries;

  @SuppressWarnings("unchecked")
  public EnumRegistry(final String type, final T defaultValue) {
    this.type = type;
    this.defaultValue = defaultValue;
    this.entries = (List<T>) List.of(defaultValue.getClass().getEnumConstants());
  }

  @Override
  public @NotNull String type() {
    return this.type;
  }

  @Override
  public T get(@NotNull String key, T def) {
    for (final T entry : this.entries) {
      if (entry.key().asString().equals(key)) {
        return entry;
      }
    }
    return def;
  }

  @Override
  public @NotNull T get(final int id) {
    return this.entries.get(id);
  }

  @Override
  public @NotNull Collection<T> entries() {
    return this.entries;
  }

  @Override
  public @NotNull T defaultValue() {
    return this.defaultValue;
  }

  @Override
  public @NotNull CompoundBinaryTag asNBT() {
    return CompoundBinaryTag.empty();
  }
}
