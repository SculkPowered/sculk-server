package io.github.sculkpowered.server.command.defaults;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.github.sculkpowered.server.SculkServer;
import io.github.sculkpowered.server.command.CommandSource;

public final class ShutdownCommand {

  public static LiteralCommandNode<CommandSource> get(final SculkServer server) {
    return LiteralArgumentBuilder.<CommandSource>literal("shutdown")
        .requires(commandSource -> commandSource.hasPermission("server.command.shutdown"))
        .executes(context -> {
          server.shutdown();
          return Command.SINGLE_SUCCESS;
        })
        .build();
  }
}
