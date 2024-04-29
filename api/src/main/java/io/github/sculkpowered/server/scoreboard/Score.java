package io.github.sculkpowered.server.scoreboard;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Score {

  @NotNull String name();

  int score();

  @Nullable Component displayName();

  @Nullable NumberFormat numberFormat();

  void update(int score, @Nullable Component displayName, @Nullable NumberFormat numberFormat);
}
