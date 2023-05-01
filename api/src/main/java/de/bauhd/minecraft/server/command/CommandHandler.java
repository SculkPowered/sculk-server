package de.bauhd.minecraft.server.command;

import org.jetbrains.annotations.NotNull;

public interface CommandHandler {

    @NotNull CommandHandler register(@NotNull BrigadierCommand command);

}
