package io.github.sculkpowered.server.attribute;

import org.jetbrains.annotations.NotNull;

public record Attribute(@NotNull String key, int id, float def, float max) {

  @Override
  public String toString() {
    return "Attribute{" +
        "key='" + this.key + '\'' +
        ", id=" + this.id +
        ", def=" + this.def +
        ", max=" + this.max +
        '}';
  }
}
