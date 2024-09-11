package eu.sculkpowered.server.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * Util class for commands.
 */
public final class Commands {

  public static LiteralArgumentBuilder<CommandSource> literal(final @NotNull String name) {
    return LiteralArgumentBuilder.literal(name);
  }

  public static <A> RequiredArgumentBuilder<CommandSource, A> argument(
      final @NotNull String name, final ArgumentType<A> type) {
    return RequiredArgumentBuilder.argument(name, type);
  }
}
