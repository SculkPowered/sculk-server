package io.github.sculkpowered.server.tag;

import io.github.sculkpowered.server.registry.Registry;
import io.github.sculkpowered.server.registry.Registry.Entry;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public final class SculkTagHandler implements TagHandler {

  private final Map<Registry<?>, Map<String, Tag<?>>> tags = new HashMap<>();

  @SuppressWarnings("unchecked")
  @Override
  public <T extends Entry> Tag<T> tag(@NotNull Registry<T> registry, @NotNull String key) {
    var tags = this.tags.get(registry);
    if (tags == null) {
      tags = new HashMap<>();
      final var tag = new SculkTag<T>(key);
      tags.put(key, tag);
      this.tags.put(registry, tags);
      return tag;
    } else {
      return (Tag<T>) this.tags.get(registry).computeIfAbsent(key, s -> new SculkTag<>(key));
    }
  }
}
