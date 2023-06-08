package de.bauhd.minecraft.server.event.player;

import de.bauhd.minecraft.server.entity.player.Player;
import de.bauhd.minecraft.server.container.Container;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class PlayerClickContainerButtonEvent {

    private final @NotNull Player player;
    private final int button;

    public PlayerClickContainerButtonEvent(@NotNull Player player, int button) {
        this.player = player;
        this.button = button;
    }

    public @NotNull Player getPlayer() {
        return this.player;
    }

    public @NotNull Container getContainer() {
        return Objects.requireNonNull(this.player.getContainer());
    }

    public int getButton() {
        return this.button;
    }
}
