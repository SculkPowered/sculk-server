package de.bauhd.sculk.event.player;

import de.bauhd.sculk.entity.player.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that fires when the client reaches the join state.
 */
public final class PlayerJoinEvent {

    private final @NotNull Player player;

    public PlayerJoinEvent(@NotNull Player player) {
        this.player = player;
    }

    public @NotNull Player getPlayer() {
        return this.player;
    }
}
