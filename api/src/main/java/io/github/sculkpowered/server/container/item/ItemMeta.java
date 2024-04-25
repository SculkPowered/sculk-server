package io.github.sculkpowered.server.container.item;

import io.github.sculkpowered.server.container.item.data.DataComponent;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public class ItemMeta {

  private final Map<DataComponent<Object>, Object> components;

  ItemMeta(final Map<DataComponent<Object>, Object> components) {
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

  public static final class Builder {

    private final Map<DataComponent<Object>, Object> components;

    private Builder(final Map<DataComponent<Object>, Object> components) {
      this.components = components;
    }

    @SuppressWarnings("unchecked")
    public @NotNull <T> Builder set(
        final @NotNull DataComponent<T> component,
        final @NotNull T value
    ) {
      this.components.put((DataComponent<Object>) component, value);
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
