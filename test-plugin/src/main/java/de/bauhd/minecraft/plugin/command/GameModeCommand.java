package de.bauhd.minecraft.plugin.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import de.bauhd.minecraft.server.command.BrigadierCommand;
import de.bauhd.minecraft.server.entity.player.GameMode;
import de.bauhd.minecraft.server.entity.player.Player;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

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
                )
                .executes(context -> {
                    context.getSource().sendMessage(Component.text("/gamemode <0-3>", NamedTextColor.RED));
                    return 1;
                })
                .build());
    }

}
