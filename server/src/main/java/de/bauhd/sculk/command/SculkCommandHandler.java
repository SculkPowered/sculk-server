package de.bauhd.sculk.command;

import static com.mojang.brigadier.CommandDispatcher.ARGUMENT_SEPARATOR_CHAR;
import static java.util.Locale.ENGLISH;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.context.CommandContextBuilder;
import com.mojang.brigadier.context.StringRange;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import de.bauhd.sculk.SculkServer;
import de.bauhd.sculk.event.command.CommandExecuteEvent;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
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
    this.server.getEventHandler().call(new CommandExecuteEvent(source, command))
        .thenAccept(event -> {
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

  public CompletableFuture<Suggestions> suggestions(CommandSource source, String command)
      throws CommandSyntaxException {
    var firstSep = command.indexOf(ARGUMENT_SEPARATOR_CHAR);
    if (firstSep != -1) {
      command =
          command.substring(0, firstSep).toLowerCase(ENGLISH) + command.substring(firstSep);
    } else {
      command = command.toLowerCase(ENGLISH);
    }
    final var contextBuilder = new CommandContextBuilder<>(this.dispatcher, source, this.root(), 0);
    final var reader = new StringReader(command);
    final var aliasRange = this.aliasRange(reader);
    final var literal = this.root().getChild(aliasRange.get(reader).toLowerCase(ENGLISH));
    if (reader.canRead()) {
      if (literal == null) {
        return Suggestions.empty();
      }
      contextBuilder.withNode(literal, aliasRange);
      reader.skip();
    }
    return this.dispatcher.getCompletionSuggestions(
        new ParseResults<>(contextBuilder, reader, Map.of()));
  }

  private StringRange aliasRange(final StringReader reader) {
    final var firstSep = reader.getString()
        .indexOf(ARGUMENT_SEPARATOR_CHAR, reader.getCursor());
    final var range = StringRange.between(
        reader.getCursor(), firstSep == -1 ? reader.getTotalLength() : firstSep);
    reader.setCursor(range.getEnd());
    return range;
  }
}
