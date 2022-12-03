package de.bauhd.minecraft.server.api.command;

import com.mojang.brigadier.CommandDispatcher;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public final class MinecraftCommandHandler implements CommandHandler {

    private final CommandDispatcher<CommandSender> dispatcher;
    private final Map<String, BrigadierCommand> commands = new HashMap<>();

    public MinecraftCommandHandler() {
        this.dispatcher = new CommandDispatcher<>();
    }

    @Override
    public @NotNull CommandHandler register(@NotNull BrigadierCommand command) {
        this.commands.put(command.node().getName(), command);
        this.dispatcher.getRoot().addChild(command.node());
        return this;
    }

    public Map<String, BrigadierCommand> commands() {
        return this.commands;
    }

    public CommandDispatcher<CommandSender> dispatcher() {
        return this.dispatcher;
    }
}
