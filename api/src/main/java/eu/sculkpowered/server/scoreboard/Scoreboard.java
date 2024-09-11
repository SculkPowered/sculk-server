package eu.sculkpowered.server.scoreboard;

import eu.sculkpowered.server.Viewable;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a scoreboard / an objective.
 */
public interface Scoreboard extends Viewable {

  /**
   * Gets the name of this scoreboard.
   * @return the name
   * @since 1.0.0
   */
  @NotNull String name();

  /**
   * Gets the display name of this scoreboard or {@code null} if it has none.
   * @return the display name
   * @since 1.0.0
   */
  @Nullable Component displayName();

  /**
   * Sets the display name of this scoreboard.
   * @param displayName the new display name
   * @since 1.0.0
   */
  void displayName(@Nullable Component displayName);

  /**
   * Gets the number format of this scoreboard or {@code null} if it has none.
   * @return the number format
   * @since 1.0.0
   */
  @Nullable NumberFormat numberFormat();

  /**
   * Sets the number format of this scoreboard.
   * @param numberFormat the new number format
   * @since 1.0.0
   */
  void numberFormat(@Nullable NumberFormat numberFormat);

  /**
   * Gets the display slot of this scoreboard.
   * @return the display slot
   * @since 1.0.0
   */
  @NotNull DisplaySlot displaySlot();

  /**
   * Sets the display slot of this scoreboard.
   * @param displaySlot the new display slot
   * @since 1.0.0
   */
  void displaySlot(@NotNull DisplaySlot displaySlot);

  /**
   * Gets a score by its name or creates one if it does not exist.
   *
   * @param name the name of the score
   * @return the score of the name
   * @since 1.0.0
   */
  @NotNull Score score(@NotNull String name);

  /**
   * Resets a score, who is specified by the name.
   * @param name the name of the score
   * @since 1.0.0
   */
  void resetScore(@NotNull String name);
}
