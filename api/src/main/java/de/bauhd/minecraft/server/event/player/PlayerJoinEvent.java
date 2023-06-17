package de.bauhd.minecraft.server.event.player;

import de.bauhd.minecraft.server.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public final class PlayerJoinEvent {

    private final @NotNull Player player;

    public PlayerJoinEvent(@NotNull Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }
}
