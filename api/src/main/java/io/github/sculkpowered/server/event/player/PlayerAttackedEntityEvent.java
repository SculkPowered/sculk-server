package io.github.sculkpowered.server.event.player;

import io.github.sculkpowered.server.entity.Entity;
import io.github.sculkpowered.server.entity.player.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that fires when a player attacks another entity.
 */
public record PlayerAttackedEntityEvent(@NotNull Player player, @NotNull Entity entity) {

}
