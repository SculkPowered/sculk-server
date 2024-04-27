package io.github.sculkpowered.server.container.item.data;

import java.util.Map;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public final class DataComponents {

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
    return new DataComponents(components);
  }
}
