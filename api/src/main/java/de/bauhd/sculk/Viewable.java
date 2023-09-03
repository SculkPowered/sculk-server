package de.bauhd.sculk;

import de.bauhd.sculk.entity.player.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * Represents an object that is viewable by players.
 */
public interface Viewable {

    /**
     * Gets the viewers of the object.
     *
     * @return the viewers
     * @since 1.0.0
     */
    @NotNull Collection<Player> getViewers();

    /**
     * Adds a viewer.
     *
     * @param player the viewer to add
     * @since 1.0.0
     */
    void addViewer(@NotNull Player player);

    /**
     * Removes a viewer.
     *
     * @param player the viewer to remove
     * @since 1.0.0
     */
    void removeViewer(@NotNull Player player);
}
