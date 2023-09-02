package de.bauhd.sculk.command.defaults;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import de.bauhd.sculk.SculkMinecraftServer;
import de.bauhd.sculk.command.CommandSource;

public final class ShutdownCommand {

    public static LiteralCommandNode<CommandSource> get(final SculkMinecraftServer server) {
        return LiteralArgumentBuilder.<CommandSource>literal("shutdown")
                .requires(commandSource -> commandSource.hasPermission("server.command.shutdown"))
                .executes(context -> {
                    server.shutdown();
                    return Command.SINGLE_SUCCESS;
                })
                .build();
    }
}
