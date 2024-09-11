package eu.sculkpowered.server.entity.player;

import java.util.Locale;
import org.jetbrains.annotations.NotNull;

/**
 * Represents the settings of the player.
 */
public interface PlayerSettings {

  /**
   * Gets the {@link Locale} of the client.
   *
   * @return the client's locale
   * @since 1.0.0
   */
  @NotNull Locale locale();

  /**
   * Gets the view distance of the client.
   *
   * @return the client's view distance
   * @since 1.0.0
   */
  byte viewDistance();

  /**
   * Gets the chat mode setting of the client.
   *
   * @return the client's chat mode setting
   * @since 1.0.0
   */
  @NotNull ChatMode chatMode();

  /**
   * Gets the chat color setting of the client.
   *
   * @return whether the client has chat colors enabled.
   * @since 1.0.0
   */
  boolean chatColorsEnabled();

  /**
   * Gets the skin parts of the client.
   *
   * @return the client's skin parts.
   * @since 1.0.0
   */
  int skinParts();

  /**
   * Gets the primary hand of the client.
   *
   * @return the client's primary hand
   * @since 1.0.0
   */
  @NotNull Hand mainHand();

  /**
   * Gets the text filtering setting of the client.
   *
   * @return whether the client has text filtering enabled.
   * @since 1.0.0
   */
  boolean textFilteringEnabled();

  /**
   * Gets the server listings setting of the client.
   *
   * @return whether the client allow server listings.
   * @since 1.0.0
   */
  boolean serverListingsAllowed();

  /**
   * The possible chat modes.
   */
  enum ChatMode {
    ENABLED,
    COMMANDS,
    HIDDEN
  }

  /**
   * The possible primary hands.
   */
  enum Hand {
    LEFT,
    RIGHT
  }
}
