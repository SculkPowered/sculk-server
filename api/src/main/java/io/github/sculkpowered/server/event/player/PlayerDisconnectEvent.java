package io.github.sculkpowered.server.event.player;

import io.github.sculkpowered.server.entity.player.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an events that fires when a player disconnects.
 *
 * @param player the player who disconnects
 */
public record PlayerDisconnectEvent(@NotNull Player player) {

}
