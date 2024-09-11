package eu.sculkpowered.server.entity.player;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an entry in the player info tab list.
 */
public interface PlayerInfoEntry {

  /**
   * Gets the {@link GameProfile} of the entry.
   *
   * @return the {@link GameProfile} of the entry
   * @since 1.0.0
   */
  @NotNull GameProfile profile();

  /**
   * Gets the game mode of the entry.
   *
   * @return the game mode of the entry
   * @since 1.0.0
   */
  @NotNull GameMode gameMode();

  /**
   * Gets the display name of the entry.
   *
   * @return the display name of the entry
   * @since 1.0.0
   */
  @Nullable Component displayName();

  /**
   * Gets the ping/latency of the entry.
   *
   * @return the ping/latency of the entry
   * @since 1.0.0
   */
  int ping();
}
