package de.bauhd.sculk.command;

import com.mojang.brigadier.tree.LiteralCommandNode;
import org.jetbrains.annotations.NotNull;

/**
 * Handles the registration and execution of commands.
 */
public interface CommandHandler {

    /**
     * Registers the command.
     * @param node the command node to register
     * @return this command handler
     */
    @NotNull CommandHandler register(@NotNull LiteralCommandNode<CommandSource> node);

    /**
     * Executes the command as the specified {@link CommandSource}.
     * @param source the source of the command
     * @param command the command to run
     */
    void execute(@NotNull CommandSource source, @NotNull String command);
}
