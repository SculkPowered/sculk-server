package io.github.sculkpowered.server.event.player;

import io.github.sculkpowered.server.entity.player.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that fires when the client reaches the join state.
 */
public record PlayerJoinEvent(@NotNull Player player) {

}
