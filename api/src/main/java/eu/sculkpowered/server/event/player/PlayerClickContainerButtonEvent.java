package eu.sculkpowered.server.event.player;

import eu.sculkpowered.server.container.Container;
import eu.sculkpowered.server.entity.player.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that fires when the client clicks at a container button.
 */
public record PlayerClickContainerButtonEvent(
    @NotNull Player player,
    @NotNull Container container,
    int button
) {

}
