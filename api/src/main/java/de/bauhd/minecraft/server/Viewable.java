package de.bauhd.minecraft.server;

import de.bauhd.minecraft.server.entity.player.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * Represents an object that is viewable by players.
 */
public interface Viewable {

    /**
     * Gets the viewers of the object.
     * @return the viewers
     */
    @NotNull Collection<Player> getViewers();

    /**
     * Adds a viewer.
     * @param player the viewer to add
     */
    void addViewer(@NotNull Player player);

    /**
     * Removes a viewer.
     * @param player the viewer to remove
     */
    void removeViewer(@NotNull Player player);
}
