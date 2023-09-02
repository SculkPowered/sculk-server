package de.bauhd.sculk.entity.player;

import java.util.Locale;

/**
 * Represents the settings of the player.
 */
public interface PlayerSettings {

    /**
     * Gets the {@link Locale} of the client.
     * @return the client's locale
     */
    Locale getLocale();

    /**
     * Gets the view distance of the client.
     * @return the client's view distance
     */
    byte getViewDistance();

    /**
     * Gets the chat mode setting of the client.
     * @return the client's chat mode setting
     */
    ChatMode getChatMode();

    /**
     * Gets the chat color setting of the client.
     * @return whether the client has chat colors enabled.
     */
    boolean enabledChatColors();

    /**
     * Gets the skin parts of the client.
     * @return the client's skin parts.
     */
    int getSkinParts();

    /**
     * Gets the primary hand of the client.
     * @return the client's primary hand
     */
    Hand getMainHand();

    /**
     * Gets the text filtering setting of the client.
     * @return whether the client has text filtering enabled.
     */
    boolean enableTextFiltering();

    /**
     * Gets the server listings setting of the client.
     * @return whether the client allow server listings.
     */
    boolean allowServerListings();

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
