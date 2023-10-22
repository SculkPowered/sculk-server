package io.github.sculkpowered.server.team;

import io.github.sculkpowered.server.SculkServer;
import io.github.sculkpowered.server.protocol.packet.play.UpdateTeams;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SculkTeamHandler implements TeamHandler {

  private final SculkServer server;
  private final Map<String, SculkTeam> teams;

  public SculkTeamHandler(final SculkServer server) {
    this.server = server;
    this.teams = new HashMap<>();
  }

  @Override
  public @NotNull Team register(@NotNull Team.Builder builder) {
    List<String> entries = new ArrayList<>();
    if (builder.entries() != null) {
      entries.addAll(Arrays.asList(builder.entries()));
    }
    final var team = new SculkTeam(this.server, builder.name(), entries, builder.displayName(),
        builder.color(), builder.prefix(), builder.suffix());
    this.server.sendAll(new UpdateTeams(team, (byte) 0, builder.entries()));
    this.teams.put(team.getName(), team);
    return team;
  }

  @Override
  public @Nullable Team getTeam(@NotNull String name) {
    return this.teams.get(name);
  }

  @Override
  public void unregister(@NotNull Team team) {
    this.server.sendAll(new UpdateTeams(team, (byte) 1, null));
    this.teams.remove(team.getName());
  }

  public Collection<SculkTeam> teams() {
    return this.teams.values();
  }
}
