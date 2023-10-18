package de.bauhd.sculk;

import de.bauhd.sculk.entity.player.Player;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;

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
   * @return whether the viewer was added
   * @since 1.0.0
   */
  boolean addViewer(@NotNull Player player);

  /**
   * Removes a viewer.
   *
   * @param player the viewer to remove
   * @return whether the viewer was removed
   * @since 1.0.0
   */
  boolean removeViewer(@NotNull Player player);
}
