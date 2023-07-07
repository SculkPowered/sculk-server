package de.bauhd.minecraft.server.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import org.jetbrains.annotations.NotNull;

/**
 * A command that uses Mojang's Brigadier for parsing
 */
public class BrigadierCommand {

    private final LiteralCommandNode<CommandSource> node;

    public BrigadierCommand(final @NotNull LiteralArgumentBuilder<CommandSource> argumentBuilder) {
        this(argumentBuilder.build());
    }

    public BrigadierCommand(final @NotNull LiteralCommandNode<CommandSource> commandNode) {
        this.node = commandNode;
    }

    public LiteralCommandNode<CommandSource> node() {
        return this.node;
    }

    public static LiteralArgumentBuilder<CommandSource> literal(final @NotNull String name) {
        return LiteralArgumentBuilder.literal(name);
    }

    public static <T> RequiredArgumentBuilder<CommandSource, T> argument(final @NotNull String name, final @NotNull ArgumentType<T> type) {
        return RequiredArgumentBuilder.argument(name, type);
    }
}
