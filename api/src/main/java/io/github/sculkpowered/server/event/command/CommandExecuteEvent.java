package io.github.sculkpowered.server.event.command;

import io.github.sculkpowered.server.command.CommandSource;
import io.github.sculkpowered.server.event.ResultedEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that fires when a command is executed.
 */
public final class CommandExecuteEvent extends ResultedEvent<ResultedEvent.GenericResult> {

  private final CommandSource source;
  private final String command;

  public CommandExecuteEvent(@NotNull CommandSource source, @NotNull String command) {
    this.source = source;
    this.command = command;
    this.result = GenericResult.allow();
  }

  public @NotNull CommandSource source() {
    return this.source;
  }

  public @NotNull String command() {
    return this.command;
  }
}
