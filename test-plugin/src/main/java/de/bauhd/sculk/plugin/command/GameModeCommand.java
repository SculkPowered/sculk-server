package de.bauhd.sculk.plugin.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import de.bauhd.sculk.command.CommandSource;
import de.bauhd.sculk.entity.player.GameMode;
import de.bauhd.sculk.entity.player.Player;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.concurrent.CompletableFuture;

public final class GameModeCommand {

    public static LiteralCommandNode<CommandSource> get() {
        return LiteralArgumentBuilder.<CommandSource>literal("gamemode")
                .then(RequiredArgumentBuilder.<CommandSource, Integer>argument("gamemode", IntegerArgumentType.integer(0, 3))
                        .executes(context -> {
                            if (context.getSource() instanceof Player player) {
                                player.setGameMode(GameMode.class.getEnumConstants()[IntegerArgumentType.getInteger(context, "gamemode")]);
                            }
                            return 1;
                        })
                        .suggests((context, builder) -> CompletableFuture.completedFuture(builder
                                .suggest(0)
                                .suggest(1)
                                .suggest(2)
                                .suggest(3)
                                .build()))
                )
                .executes(context -> {
                    context.getSource().sendMessage(Component.text("/gamemode <0-3>", NamedTextColor.RED));
                    return Command.SINGLE_SUCCESS;
                })
                .build();
    }
}
