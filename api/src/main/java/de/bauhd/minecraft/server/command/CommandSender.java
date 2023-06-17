package de.bauhd.minecraft.server.command;

import net.kyori.adventure.audience.Audience;
import org.jetbrains.annotations.NotNull;

public interface CommandSender extends Audience {

    boolean hasPermission(@NotNull String permission);
}
