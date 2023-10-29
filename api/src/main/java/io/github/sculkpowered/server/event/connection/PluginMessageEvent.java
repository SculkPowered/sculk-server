package io.github.sculkpowered.server.event.connection;

import io.github.sculkpowered.server.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public record PluginMessageEvent(
    @NotNull Player player,
    @NotNull String identifier,
    byte @NotNull [] data
) {

}
