package de.bauhd.minecraft.server.command;

import net.kyori.adventure.audience.Audience;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an object that can send a command.
 */
public interface CommandSender extends Audience {

    boolean hasPermission(@NotNull String permission);
}
