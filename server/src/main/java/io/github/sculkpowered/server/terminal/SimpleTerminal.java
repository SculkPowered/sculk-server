package io.github.sculkpowered.server.terminal;

import io.github.sculkpowered.server.SculkServer;
import io.github.sculkpowered.server.command.CommandSource;
import java.io.IOException;
import net.kyori.adventure.chat.ChatType;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.permission.PermissionChecker;
import net.kyori.adventure.pointer.Pointers;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.kyori.adventure.util.TriState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;
import org.jetbrains.annotations.NotNull;
import org.jline.reader.Candidate;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReader.Option;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.TerminalBuilder;

public final class SimpleTerminal implements CommandSource {

  private static final Logger LOGGER = LogManager.getLogger(SimpleTerminal.class);
  private static final ComponentLogger COMPONENT_LOGGER = ComponentLogger.logger(
      SimpleTerminal.class);
  private static final String PROMPT = "> ";

  private final SculkServer server;
  private final LineReader lineReader;
  private final Pointers pointers = Pointers.builder()
      .withStatic(Identity.NAME, "CONSOLE")
      .withStatic(PermissionChecker.POINTER, PermissionChecker.always(TriState.TRUE))
      .build();

  public SimpleTerminal(final SculkServer server) {
    this.server = server;
    try {
      this.lineReader = LineReaderBuilder.builder()
          .appName("Sculk-Server")
          .terminal(TerminalBuilder.builder()
              .dumb(true)
              .build())
          .option(Option.INSERT_TAB, false)
          .option(Option.DISABLE_EVENT_EXPANSION, true)
          .completer((reader, line, candidates) -> {
            try {
              final var suggestions = this.server.commandHandler()
                  .suggestions(this, line.line()).join();
              for (final var suggestion : suggestions.getList()) {
                candidates.add(new Candidate(suggestion.getText()));
              }
            } catch (Exception e) {
              LOGGER.error("Exception during terminal completion", e);
            }
          })
          .build();
      TerminalAppender.setLineReader(this.lineReader);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void setupStreams() {
    System.setOut(IoBuilder.forLogger(LOGGER).setLevel(Level.INFO).buildPrintStream());
    System.setErr(IoBuilder.forLogger(LOGGER).setLevel(Level.ERROR).buildPrintStream());
  }

  public void start() {
    while (this.server.isRunning()) {
      try {
        final var line = this.lineReader.readLine(PROMPT).trim();
        if (!line.isEmpty()) {
          this.server.commandHandler().execute(this.server.consoleCommandSource(), line);
        }
      } catch (EndOfFileException ignored) {
      } catch (UserInterruptException e) {
        this.server.shutdown(true);
      }
    }
  }

  @Override
  public boolean hasPermission(@NotNull String permission) {
    return true;
  }

  @Override
  public void sendMessage(@NotNull Component message) {
    COMPONENT_LOGGER.info(message);
  }

  @Override
  public void sendMessage(@NotNull Component message, ChatType.@NotNull Bound boundChatType) {
    COMPONENT_LOGGER.info(message);
  }

  @Override
  public @NotNull Pointers pointers() {
    return this.pointers;
  }
}
