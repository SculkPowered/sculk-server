package io.github.sculkpowered.server.scoreboard;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a scoreboard score.
 */
public interface Score {

  /**
   * Gets the name of the score.
   * @return the name
   * @since 1.0.0
   */
  @NotNull String name();

  /**
   * Gets the score value of the score.
   * @return the score value
   * @since 1.0.0
   */
  int score();

  /**
   * Gets the display name of the score or {@code null} if the score has none.
   * @return the display name
   * @since 1.0.0
   */
  @Nullable Component displayName();

  /**
   * Gets the number format of the score or {@code null} if the score has none.
   * @return the number format
   * @since 1.0.0
   */
  @Nullable NumberFormat numberFormat();

  /**
   * Sets the score value of the score.
   * @param score the new score value
   * @since 1.0.0
   */
  default void set(int score) {
    this.set(score, this.displayName());
  }

  /**
   * Sets the score value and display name of the score.
   * @param score the new score value
   * @param displayName the new display name
   * @since 1.0.0
   */
  default void set(int score, @Nullable Component displayName) {
    this.set(score, displayName, this.numberFormat());
  }

  /**
   * Sets the score attributes.
   * @param score the new score value
   * @param displayName the new display name
   * @param numberFormat the new number format
   * @since 1.0.0
   */
  void set(int score, @Nullable Component displayName, @Nullable NumberFormat numberFormat);
}
