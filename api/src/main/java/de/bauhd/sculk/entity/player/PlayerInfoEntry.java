package de.bauhd.sculk.entity.player;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an entry in the player info tab list.
 */
public interface PlayerInfoEntry {

    /**
     * Gets the {@link GameProfile} of the entry.
     * @return the {@link GameProfile} of the entry
     */
    @NotNull GameProfile getProfile();

    /**
     * Gets the game mode of the entry.
     * @return the game mode of the entry
     */
    @NotNull GameMode getGameMode();

    /**
     * Gets the display name of the entry.
     * @return the display name of the entry
     */
    @Nullable Component getDisplayName();

    /**
     * Gets the ping/latency of the entry.
     * @return the ping/latency of the entry
     */
    int getPing();

}
