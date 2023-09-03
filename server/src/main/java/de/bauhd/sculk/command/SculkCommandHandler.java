package de.bauhd.sculk.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import de.bauhd.sculk.SculkServer;
import de.bauhd.sculk.event.command.CommandExecuteEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class SculkCommandHandler implements CommandHandler {

    private static final Logger LOGGER = LogManager.getLogger(SculkCommandHandler.class);

    private final SculkServer server;
    private final CommandDispatcher<CommandSource> dispatcher;

    public SculkCommandHandler(final SculkServer server) {
        this.server = server;
        this.dispatcher = new CommandDispatcher<>();
    }

    @Override
    public @NotNull CommandHandler register(@NotNull LiteralCommandNode<CommandSource> node) {
        this.dispatcher.getRoot().addChild(node);
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

    public RootCommandNode<CommandSource> root() {
        return this.dispatcher.getRoot();
    }
}
