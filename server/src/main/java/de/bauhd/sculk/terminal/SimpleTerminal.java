package de.bauhd.sculk.terminal;

import de.bauhd.sculk.SculkMinecraftServer;
import de.bauhd.sculk.command.CommandSource;
import de.bauhd.terminal.TerminalAppender;
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
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public final class SimpleTerminal implements CommandSource {

    private static final Logger LOGGER = LogManager.getLogger(SimpleTerminal.class);
    private static final ComponentLogger COMPONENT_LOGGER = ComponentLogger.logger(SimpleTerminal.class);
    private static final String PROMPT = "> ";

    private final SculkMinecraftServer server;
    private final LineReader lineReader;
    private final Pointers pointers = Pointers.builder()
            .withStatic(Identity.NAME, "CONSOLE")
            .withStatic(PermissionChecker.POINTER, PermissionChecker.always(TriState.TRUE))
            .build();

    public SimpleTerminal(final SculkMinecraftServer server) {
        this.server = server;
        try {
            this.lineReader = LineReaderBuilder.builder()
                    .appName("Sculk-Server")
                    .terminal(TerminalBuilder.builder()
                            .dumb(true)
                            .build())
                    .option(LineReader.Option.INSERT_TAB, false)
                    .option(LineReader.Option.DISABLE_EVENT_EXPANSION, true)
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
                    this.server.getCommandHandler().execute(this.server.getConsoleCommandSource(), line);
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
