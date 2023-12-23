package io.github.sculkpowered.server.tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public final class SculkTag<T> implements Tag<T> {

  private final String key;
  private final List<T> entries;

  public SculkTag(final String key) {
    this.key = key;
    this.entries = new ArrayList<>();
  }

  @Override
  public @NotNull String key() {
    return this.key;
  }

  @Override
  public @NotNull Collection<T> entries() {
    return this.entries;
  }
}
