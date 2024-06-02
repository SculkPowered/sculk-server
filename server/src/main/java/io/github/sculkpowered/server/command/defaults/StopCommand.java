package io.github.sculkpowered.server.command.defaults;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.github.sculkpowered.server.SculkServer;
import io.github.sculkpowered.server.command.CommandSource;
import io.github.sculkpowered.server.command.Commands;

public final class StopCommand {

  public static LiteralCommandNode<CommandSource> get(final SculkServer server) {
    return Commands.literal("stop")
        .requires(commandSource -> commandSource.hasPermission("server.command.stop"))
        .executes(context -> {
          server.shutdown();
          return Command.SINGLE_SUCCESS;
        })
        .build();
  }
}
