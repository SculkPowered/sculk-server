package io.github.sculkpowered.server.event.command;

import io.github.sculkpowered.server.command.CommandSource;
import io.github.sculkpowered.server.event.ResultedEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that fires when a command is executed.
 */
public final class CommandExecuteEvent extends ResultedEvent<ResultedEvent.GenericResult> {

  private final @NotNull CommandSource source;
  private final @NotNull String command;

  public CommandExecuteEvent(@NotNull CommandSource source, @NotNull String command) {
    this.source = source;
    this.command = command;
    this.result = GenericResult.allowed();
  }

  public @NotNull CommandSource getSource() {
    return this.source;
  }

  public @NotNull String getCommand() {
    return this.command;
  }
}