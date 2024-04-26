package io.github.sculkpowered.server.container.item;

import io.github.sculkpowered.server.container.item.data.DataComponent;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemMeta {

  private final Map<DataComponent<?>, Optional<?>> components;

  ItemMeta(final Map<DataComponent<?>, Optional<?>> components) {
    this.components = Map.copyOf(components);
  }

  @SuppressWarnings("unchecked")
  public <T> T get(final @NotNull DataComponent<T> component) {
    return (T) this.components.get(component);
  }

  public @NotNull ItemMeta.Builder toBuilder() {
    return new Builder(new HashMap<>(this.components));
  }

  public static @NotNull Builder builder() {
    return new Builder(new HashMap<>());
  }

  public Map<DataComponent<?>, Optional<?>> components() {
    return this.components;
  }

  public static final class Builder {

    private final Map<DataComponent<?>, Optional<?>> components;

    private Builder(final Map<DataComponent<?>, Optional<?>> components) {
      this.components = components;
    }

    public @NotNull <T> Builder set(
        final @NotNull DataComponent<T> component,
        final @Nullable T value
    ) {
      this.components.put(component, Optional.ofNullable(value));
      return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(final @NotNull DataComponent<T> component) {
      return (T) this.components.get(component);
    }

    public @NotNull ItemMeta build() {
      return new ItemMeta(this.components);
    }
  }
}
