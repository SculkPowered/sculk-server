package de.bauhd.minecraft.server.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.RootCommandNode;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public final class MineCommandHandler implements CommandHandler {

    private final CommandDispatcher<CommandSender> dispatcher;
    private final Map<String, BrigadierCommand> commands = new HashMap<>();

    public MineCommandHandler() {
        this.dispatcher = new CommandDispatcher<>();
    }

    @Override
    public @NotNull CommandHandler register(@NotNull BrigadierCommand command) {
        this.commands.put(command.node().getName(), command);
        this.dispatcher.getRoot().addChild(command.node());
        return this;
    }

    @Override
    public void execute(@NotNull CommandSender sender, @NotNull String command) {
        try {
            this.dispatcher.execute(command, sender);
        } catch (CommandSyntaxException e) {
            sender.sendMessage(Component.text(e.getMessage(), NamedTextColor.RED));
        }
    }

    public Map<String, BrigadierCommand> commands() {
        return this.commands;
    }

    public RootCommandNode<CommandSender> root() {
        return this.dispatcher.getRoot();
    }
}
