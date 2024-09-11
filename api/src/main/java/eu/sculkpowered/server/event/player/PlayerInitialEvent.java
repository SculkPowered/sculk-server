package eu.sculkpowered.server.event.player;

import eu.sculkpowered.server.entity.player.GameMode;
import eu.sculkpowered.server.entity.player.Player;
import eu.sculkpowered.server.world.Position;
import eu.sculkpowered.server.world.World;
import net.kyori.adventure.permission.PermissionChecker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an event that fires when the client .
 */
public final class PlayerInitialEvent {

  private final Player player;
  private World world;
  private GameMode gameMode;
  private Position position;
  private PermissionChecker permissionChecker;

  public PlayerInitialEvent(@NotNull Player player) {
    this.player = player;
  }

  public @NotNull Player player() {
    return this.player;
  }

  public void world(@Nullable World world) {
    this.world = world;
  }

  public @Nullable World world() {
    return this.world;
  }

  public @Nullable GameMode gameMode() {
    return gameMode;
  }

  public void gameMode(@Nullable GameMode gameMode) {
    this.gameMode = gameMode;
  }

  public @Nullable Position position() {
    return position;
  }

  public void position(@Nullable Position position) {
    this.position = position;
  }

  public @Nullable PermissionChecker permissionChecker() {
    return permissionChecker;
  }

  public void permissionChecker(@Nullable PermissionChecker permissionChecker) {
    this.permissionChecker = permissionChecker;
  }
}
