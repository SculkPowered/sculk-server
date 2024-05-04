package io.github.sculkpowered.server.scoreboard;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Score {

  @NotNull String name();

  int score();

  @Nullable Component displayName();

  @Nullable NumberFormat numberFormat();

  default void set(int score) {
    this.set(score, this.displayName());
  }

  default void set(int score, @Nullable Component displayName) {
    this.set(score, displayName, this.numberFormat());
  }

  void set(int score, @Nullable Component displayName, @Nullable NumberFormat numberFormat);
}
