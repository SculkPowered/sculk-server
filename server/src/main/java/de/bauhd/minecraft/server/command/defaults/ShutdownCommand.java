package de.bauhd.minecraft.server.command.defaults;

import com.mojang.brigadier.Command;
import de.bauhd.minecraft.server.MinecraftServer;
import de.bauhd.minecraft.server.command.BrigadierCommand;

public final class ShutdownCommand extends BrigadierCommand {

    public ShutdownCommand(final MinecraftServer server) {
        super(literal("shutdown")
                .requires(commandSender -> commandSender.hasPermission("server.command.shutdown"))
                .executes(context -> {
                    server.shutdown();
                    return Command.SINGLE_SUCCESS;
                })
                .build());
    }
}
