package de.bauhd.minecraft.plugin.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import de.bauhd.minecraft.server.command.BrigadierCommand;
import de.bauhd.minecraft.server.entity.player.GameMode;
import de.bauhd.minecraft.server.entity.player.Player;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.concurrent.CompletableFuture;

public final class GameModeCommand extends BrigadierCommand {

    public GameModeCommand() {
        super(literal("gamemode")
                .then(argument("gamemode", IntegerArgumentType.integer(0, 3))
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
                .build());
    }

}
