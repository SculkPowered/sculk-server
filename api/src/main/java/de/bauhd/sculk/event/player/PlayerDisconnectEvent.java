package de.bauhd.sculk.event.player;

import de.bauhd.sculk.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public final class PlayerDisconnectEvent {

    private final @NotNull Player player;

    public PlayerDisconnectEvent(@NotNull Player player) {
        this.player = player;
    }

    public @NotNull Player getPlayer() {
        return this.player;
    }
}
