package de.bauhd.minecraft.server.api.command;

import org.jetbrains.annotations.NotNull;

public interface CommandHandler {

    @NotNull CommandHandler register(@NotNull String name, @NotNull BrigadierCommand command);

}
