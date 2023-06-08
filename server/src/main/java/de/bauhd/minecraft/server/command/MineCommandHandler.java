package de.bauhd.minecraft.server.command;

import com.mojang.brigadier.CommandDispatcher;
import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.command.defaults.ShutdownCommand;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public final class MineCommandHandler implements CommandHandler {

    private final CommandDispatcher<CommandSender> dispatcher;
    private final Map<String, BrigadierCommand> commands = new HashMap<>();

    public MineCommandHandler(final AdvancedMinecraftServer server) {
        this.dispatcher = new CommandDispatcher<>();

        // register default commands
        this.register(new ShutdownCommand(server));
        this.register(new InfoCommand().get());
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
