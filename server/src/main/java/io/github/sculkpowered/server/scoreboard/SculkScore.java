package io.github.sculkpowered.server.scoreboard;

import io.github.sculkpowered.server.protocol.packet.play.scoreboard.UpdateScore;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SculkScore implements Score {

  private final SculkScoreboard scoreboard;
  private final String name;
  private int score;
  private Component displayName;
  private NumberFormat numberFormat;

  public SculkScore(
      final SculkScoreboard scoreboard,
      final String name
  ) {
    this.scoreboard = scoreboard;
    this.name = name;
  }

  @Override
  public @NotNull String name() {
    return this.name;
  }

  @Override
  public int score() {
    return this.score;
  }

  @Override
  public @Nullable Component displayName() {
    return this.displayName;
  }

  @Override
  public @Nullable NumberFormat numberFormat() {
    return this.numberFormat;
  }

  @Override
  public void update(
      int score,
      @Nullable Component displayName,
      @Nullable NumberFormat numberFormat
  ) {
    this.score = score;
    this.displayName = displayName;
    this.numberFormat = numberFormat;
    this.scoreboard.sendViewers(new UpdateScore(
        this.name, this.scoreboard.name(), this.score, this.displayName, this.numberFormat));
  }
}
