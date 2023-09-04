package de.bauhd.sculk.event.player;

import de.bauhd.sculk.connection.Connection;
import de.bauhd.sculk.entity.player.GameMode;
import de.bauhd.sculk.entity.player.Player;
import de.bauhd.sculk.world.Position;
import de.bauhd.sculk.world.World;
import net.kyori.adventure.permission.PermissionChecker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an event that fires when the client .
 */
public final class PlayerInitialEvent {

    private final @NotNull Player player;
    private @Nullable World world;
    private @Nullable GameMode gameMode;
    private @Nullable Position position;
    private @Nullable PermissionChecker permissionChecker;

    public PlayerInitialEvent(@NotNull Player player) {
        this.player = player;
    }

    public @NotNull Connection getConnection() {
        return this.player;
    }

    public void setWorld(@Nullable World world) {
        this.world = world;
    }

    public @Nullable World getWorld() {
        return this.world;
    }

    public @Nullable GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(@Nullable GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public @Nullable Position getPosition() {
        return position;
    }

    public void setPosition(@Nullable Position position) {
        this.position = position;
    }

    public @Nullable PermissionChecker getPermissionChecker() {
        return permissionChecker;
    }

    public void setPermissionChecker(@Nullable PermissionChecker permissionChecker) {
        this.permissionChecker = permissionChecker;
    }
}