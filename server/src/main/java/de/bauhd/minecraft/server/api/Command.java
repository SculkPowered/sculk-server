package de.bauhd.minecraft.server.api;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import de.bauhd.minecraft.server.api.command.BrigadierCommand;
import de.bauhd.minecraft.server.api.command.CommandSender;
import net.kyori.adventure.text.Component;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;

public class Command extends BrigadierCommand {

    public Command() {
        super(LiteralArgumentBuilder.<CommandSender>literal("foo")
                .then(RequiredArgumentBuilder.<CommandSender, Integer>argument("bar", IntegerArgumentType.integer())
                                .executes(context -> {
                                    context.getSource().sendMessage(Component.text("Bar is " + getInteger(context, "bar")));
                                    return 1;
                                })
                )
                .executes(context -> {
                    context.getSource().sendMessage(Component.text("Called foo with no arguments"));
                    return 1;
                }));
    }
}
