package de.bauhd.minecraft.server.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.RootCommandNode;
import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.event.command.CommandExecuteEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public final class MineCommandHandler implements CommandHandler {

    private static final Logger LOGGER = LogManager.getLogger(MineCommandHandler.class);

    private final AdvancedMinecraftServer server;
    private final CommandDispatcher<CommandSource> dispatcher;
    private final Map<String, BrigadierCommand> commands = new HashMap<>();

    public MineCommandHandler(final AdvancedMinecraftServer server) {
        this.server = server;
        this.dispatcher = new CommandDispatcher<>();
    }

    @Override
    public @NotNull CommandHandler register(@NotNull BrigadierCommand command) {
        this.commands.put(command.node().getName(), command);
        this.dispatcher.getRoot().addChild(command.node());
        return this;
    }

    @Override
    public void execute(@NotNull CommandSource source, @NotNull String command) {
        this.server.getEventHandler().call(new CommandExecuteEvent(source, command)).thenAccept(event -> {
            try {
                this.dispatcher.execute(command, source);
            } catch (CommandSyntaxException e) {
                source.sendMessage(Component.text(e.getMessage(), NamedTextColor.RED));
            }
        }).exceptionally(throwable -> {
            LOGGER.error("Exception while executing command.", throwable);
            return null;
        });
    }

    public Map<String, BrigadierCommand> commands() {
        return this.commands;
    }

    public RootCommandNode<CommandSource> root() {
        return this.dispatcher.getRoot();
    }
}
