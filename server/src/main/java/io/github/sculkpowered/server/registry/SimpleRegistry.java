package io.github.sculkpowered.server.registry;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import org.jetbrains.annotations.NotNull;

public class SimpleRegistry<E extends Registry.Entry> implements Registry<E> {

  private final Map<String, E> byKey = new HashMap<>();
  private final Int2ObjectMap<E> byId = new Int2ObjectOpenHashMap<>();
  private final String type;
  private final E def;

  public SimpleRegistry(@NotNull String type, @NotNull E def) {
    this.type = type;
    this.def = def;
    this.register(def);
  }

  @Override
  public @NotNull String type() {
    return this.type;
  }

  @Override
  public void register(@NotNull E entry) {
    this.byKey.put(entry.name(), entry);
    this.byId.put(entry.id(), entry);
  }

  @Override
  public @NotNull E get(@NotNull String key) {
    return this.byKey.getOrDefault(key, this.def);
  }

  @Override
  public @NotNull E get(int id) {
    return this.byId.get(id);
  }

  @Override
  public @NotNull Collection<E> entries() {
    return this.byKey.values();
  }

  @Override
  public @NotNull CompoundBinaryTag asNBT() {
    final var list = ListBinaryTag.builder();
    for (final var value : this.entries()) {
      list.add(value.asNBT());
    }
    return CompoundBinaryTag.builder()
        .putString("type", this.type)
        .put("value", list.build())
        .build();
  }
}