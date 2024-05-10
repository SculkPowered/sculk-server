package io.github.sculkpowered.server.container.item.data;

import io.github.sculkpowered.server.registry.Registries;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;

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
}
