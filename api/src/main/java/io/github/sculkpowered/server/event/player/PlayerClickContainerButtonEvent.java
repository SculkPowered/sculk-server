package io.github.sculkpowered.server.event.player;

import io.github.sculkpowered.server.container.Container;
import io.github.sculkpowered.server.entity.player.Player;
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
