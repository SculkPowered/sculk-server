package io.github.sculkpowered.server.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.sculkpowered.server.Server;
import io.github.sculkpowered.server.team.Team;
import io.github.sculkpowered.server.team.TeamHandler;

public final class TeamArgumentType implements ArgumentType<Team> {

  private final TeamHandler teamHandler;

  public TeamArgumentType(final TeamHandler teamHandler) {
    this.teamHandler = teamHandler;
  }

  public static TeamArgumentType team(final Server server) {
    return team(server.teamHandler());
  }

  public static TeamArgumentType team(final TeamHandler teamHandler) {
    return new TeamArgumentType(teamHandler);
  }

  @Override
  public Team parse(StringReader reader) throws CommandSyntaxException {
    final var team = this.teamHandler.team(reader.readUnquotedString());
    if (team == null) {
      throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherUnknownArgument()
          .createWithContext(reader);
    }
    return team;
  }
}
