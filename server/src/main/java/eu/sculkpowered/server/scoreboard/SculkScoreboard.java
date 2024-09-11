package eu.sculkpowered.server.scoreboard;

import eu.sculkpowered.server.entity.player.Player;
import eu.sculkpowered.server.entity.player.SculkPlayer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetDisplayObjectivePacket;
import eu.sculkpowered.server.protocol.packet.clientbound.ResetScorePacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetObjectivePacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetScorePacket;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SculkScoreboard implements Scoreboard {

  private final String name;
  private Component displayName;
  private NumberFormat numberFormat;
  private DisplaySlot displaySlot;
  private final Set<SculkPlayer> viewers = new HashSet<>();
  private final Map<String, Score> scores = new HashMap<>();

  public SculkScoreboard(
      final String name,
      final Component displayName,
      final NumberFormat numberFormat,
      final DisplaySlot displaySlot
  ) {
    this.name = name;
    this.displayName = displayName;
    this.numberFormat = numberFormat;
    this.displaySlot = displaySlot;
  }

  @Override
  public @NotNull String name() {
    return this.name;
  }

  @Override
  public @Nullable Component displayName() {
    return this.displayName;
  }

  @Override
  public void displayName(@Nullable Component displayName) {
    this.displayName = displayName;
    this.update();
  }

  @Override
  public @Nullable NumberFormat numberFormat() {
    return this.numberFormat;
  }

  @Override
  public void numberFormat(@Nullable NumberFormat numberFormat) {
    this.numberFormat = numberFormat;
    this.update();
  }

  @Override
  public @NotNull DisplaySlot displaySlot() {
    return this.displaySlot;
  }

  @Override
  public void displaySlot(@NotNull DisplaySlot displaySlot) {
    this.displaySlot = displaySlot;
    this.sendViewers(new SetDisplayObjectivePacket((byte) this.displaySlot.ordinal(), this.name));
  }

  @Override
  public @NotNull Score score(@NotNull String name) {
    return this.scores.computeIfAbsent(name, s -> new SculkScore(this, name));
  }

  @Override
  public void resetScore(@NotNull String name) {
    if (this.scores.remove(name) != null) {
      this.sendViewers(new ResetScorePacket(name, this.name));
    }
  }

  @Override
  public @NotNull Collection<Player> viewers() {
    return List.copyOf(this.viewers);
  }

  @Override
  public boolean addViewer(@NotNull Player player) {
    final var sculkPlayer = (SculkPlayer) player;
    if (this.viewers.add(sculkPlayer)) {
      sculkPlayer.send(SetObjectivePacket
          .create(this.name, this.displayName, (byte) 0, this.numberFormat));
      sculkPlayer.send(new SetDisplayObjectivePacket((byte) this.displaySlot.ordinal(), this.name));
      for (final var score : this.scores.values()) {
        sculkPlayer.send(new SetScorePacket(
            score.name(), this.name, score.score(), score.displayName(), score.numberFormat()));
      }
      return true;
    }
    return false;
  }

  @Override
  public boolean removeViewer(@NotNull Player player) {
    final var sculkPlayer = (SculkPlayer) player;
    if (this.viewers.remove(sculkPlayer)) {
      sculkPlayer.send(SetObjectivePacket.remove(this.name));
      return true;
    }
    return false;
  }

  private void update() {
    this.sendViewers(SetObjectivePacket.update(this.name, this.displayName, (byte) 0, this.numberFormat));
  }

  void sendViewers(final ClientboundPacket packet) {
    for (final var viewer : this.viewers) {
      viewer.send(packet);
    }
  }
}
