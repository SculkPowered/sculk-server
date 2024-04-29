package io.github.sculkpowered.server.scoreboard;

import io.github.sculkpowered.server.Viewable;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Scoreboard extends Viewable {

  @NotNull String name();

  @Nullable Component displayName();

  void displayName(@Nullable Component displayName);

  @Nullable NumberFormat numberFormat();

  void numberFormat(@Nullable NumberFormat numberFormat);

  @NotNull DisplaySlot displaySlot();

  void displaySlot(@NotNull DisplaySlot displaySlot);

  @NotNull Score score(@NotNull String name);

  void resetScore(@NotNull String name);
}
