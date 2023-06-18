package de.bauhd.minecraft.server.command;

import org.jetbrains.annotations.NotNull;

/**
 * Handles the registration and execution of commands.
 */
public interface CommandHandler {

    /**
     * Registers the command.
     * @param command the command to register
     * @return this command handler
     */
    @NotNull CommandHandler register(@NotNull BrigadierCommand command);

    /**
     * Executes the command as the specified {@link CommandSender}.
     * @param sender the sender of the command
     * @param command the command to run
     */
    void execute(@NotNull CommandSender sender, @NotNull String command);
}
