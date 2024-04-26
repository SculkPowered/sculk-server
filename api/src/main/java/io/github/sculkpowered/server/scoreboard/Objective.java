package io.github.sculkpowered.server.scoreboard;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Objective {

  @NotNull String name();

  @Nullable Component displayName();

  void displayName(@Nullable Component displayName);

  @Nullable NumberFormat numberFormat();

  void numberFormat(@Nullable NumberFormat displayName);
}
