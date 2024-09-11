package eu.sculkpowered.server.event.player;

import eu.sculkpowered.server.entity.Entity;
import eu.sculkpowered.server.entity.player.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that fires when a player attacks another entity.
 */
public record PlayerAttackedEntityEvent(@NotNull Player player, @NotNull Entity entity) {

}
