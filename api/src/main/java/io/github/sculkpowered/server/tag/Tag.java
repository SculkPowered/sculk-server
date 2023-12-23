package io.github.sculkpowered.server.tag;

import java.util.Collection;
import org.jetbrains.annotations.NotNull;

public interface Tag<T> {

  @NotNull String key();

  @NotNull Collection<T> entries();
}
