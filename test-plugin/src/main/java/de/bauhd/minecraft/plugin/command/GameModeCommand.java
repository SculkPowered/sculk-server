package de.bauhd.minecraft.plugin.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import de.bauhd.minecraft.server.api.command.BrigadierCommand;
import de.bauhd.minecraft.server.api.command.CommandSender;
import de.bauhd.minecraft.server.api.entity.player.GameMode;
import de.bauhd.minecraft.server.api.entity.player.Player;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public final class GameModeCommand extends BrigadierCommand {

    public GameModeCommand() {
        super(LiteralArgumentBuilder.<CommandSender>literal("gamemode")
                .then(RequiredArgumentBuilder.<CommandSender, Integer>argument("gamemode", IntegerArgumentType.integer(0, 3))
                        .executes(context -> {
                            if (context.getSource() instanceof Player player) {
                                player.setGameMode(GameMode.class.getEnumConstants()[IntegerArgumentType.getInteger(context, "gamemode")]);
                            }
                            return 1;
                        })
                )
                .executes(context -> {
                    context.getSource().sendMessage(Component.text("/gamemode <0-3>", NamedTextColor.RED));
                    return 1;
                })
                .build());
    }

}
