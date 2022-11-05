package de.bauhd.minecraft.server.api.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import org.jetbrains.annotations.NotNull;

public class BrigadierCommand {

    private final LiteralCommandNode<CommandSender> commandNode;

    public BrigadierCommand(final @NotNull LiteralArgumentBuilder<CommandSender> argumentBuilder) {
        this(argumentBuilder.build());
    }

    public BrigadierCommand(final @NotNull LiteralCommandNode<CommandSender> commandNode) {
        this.commandNode = commandNode;
    }

    public LiteralCommandNode<CommandSender> commandNode() {
        return this.commandNode;
    }
}
