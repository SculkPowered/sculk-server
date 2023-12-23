package io.github.sculkpowered.server.tag;

import io.github.sculkpowered.server.registry.Registry;
import io.github.sculkpowered.server.registry.Registry.Entry;
import org.jetbrains.annotations.NotNull;

public interface TagHandler {

  <T extends Entry> Tag<T> tag(@NotNull Registry<T> registry, @NotNull String identifier);
}
