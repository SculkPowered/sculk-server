package io.github.sculkpowered.server.event.player;

import io.github.sculkpowered.server.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public record PlayerDisconnectEvent(@NotNull Player player) {

}
