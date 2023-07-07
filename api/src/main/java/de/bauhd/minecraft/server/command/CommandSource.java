package de.bauhd.minecraft.server.command;

import net.kyori.adventure.audience.Audience;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an object that is a source of a command.
 */
public interface CommandSource extends Audience {

    boolean hasPermission(@NotNull String permission);
}
