package eu.sculkpowered.server.container.item.data;

import eu.sculkpowered.server.registry.Registries;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DataComponents {

  private static final char REMOVE_CHAR = '!';
  private static final DataComponents EMPTY = new DataComponents(Map.of());

  private final Map<DataComponentType<?>, Optional<?>> components;

  DataComponents(final Map<DataComponentType<?>, Optional<?>> components) {
    this.components = components;
  }

  public Map<DataComponentType<?>, Optional<?>> components() {
    return this.components;
  }

  @SuppressWarnings("unchecked")
  public @NotNull CompoundBinaryTag asNBT() {
    final var builder = CompoundBinaryTag.builder();
    for (final var entry : this.components.entrySet()) {
      final var type = (DataComponentType<Object>) entry.getKey();
      final var value = entry.getValue();
      if (type.serializable()) {
        if (value.isPresent()) {
          builder.put(type.key().asString(), type.valueToBinary(value.get()));
        } else {
          builder.put(REMOVE_CHAR + type.key().asString(), CompoundBinaryTag.empty());
        }
      }
    }
    return builder.build();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof DataComponents dataComponents) {
      return this.components.equals(dataComponents.components);
    }
    return super.equals(obj);
  }

  @Override
  public String toString() {
    return "DataComponents{" +
        "components=" + this.components +
        '}';
  }

  public @NotNull DataComponents.Builder toBuilder() {
    return new Builder(new HashMap<>(this.components));
  }

  @SuppressWarnings("unchecked")
  public <T> @Nullable Optional<T> get(final @NotNull DataComponentType<T> type) {
    return (Optional<T>) this.components.get(type);
  }

  public static @NotNull DataComponents empty() {
    return EMPTY;
  }

  public static @NotNull DataComponents from(
      final @NotNull Map<DataComponentType<?>, Optional<?>> components
  ) {
    return new DataComponents(Map.copyOf(components));
  }

  public static @NotNull DataComponents from(final @NotNull CompoundBinaryTag compoundTag) {
    final var map = new HashMap<DataComponentType<?>, Optional<?>>();
    for (final var entry : compoundTag) {
      if (entry.getKey().charAt(0) == REMOVE_CHAR) {
        map.put(Registries.dataComponentTypes()
            .get(entry.getKey().substring(1)), Optional.empty());
        continue;
      }
      final var type = Registries.dataComponentTypes().get(entry.getKey());
      map.put(type, Optional.of(type.binaryToValue(entry.getValue())));
    }
    return new DataComponents(map);
  }

  public static @NotNull DataComponents.Builder builder() {
    return new Builder(new HashMap<>());
  }

  public static final class Builder {

    private final Map<DataComponentType<?>, Optional<?>> components;

    private Builder(final Map<DataComponentType<?>, Optional<?>> components) {
      this.components = components;
    }

    public <T> @NotNull Builder set(final @NotNull DataComponentType<T> type, final @NotNull T t) {
      this.components.put(type, Optional.of(t));
      return this;
    }

    public @NotNull Builder enable(final @NotNull DataComponentType<Unit> type) {
      return this.set(type, Unit.INSTANCE);
    }

    public @NotNull Builder unset(final @NotNull DataComponentType<?> type) {
      this.components.put(type, Optional.empty());
      return this;
    }

    public @NotNull Builder reset(final @NotNull DataComponentType<?> type) {
      this.components.remove(type);
      return this;
    }

    @SuppressWarnings("unchecked")
    public <T> @Nullable Optional<T> get(final @NotNull DataComponentType<T> type) {
      return (Optional<T>) this.components.get(type);
    }

    public @NotNull DataComponents build() {
      return DataComponents.from(this.components);
    }
  }
}
