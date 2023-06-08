package de.bauhd.minecraft.server.entity.player;

import java.util.Locale;

public interface PlayerSettings {

    Locale getLocale();

    byte getViewDistance();

    ChatMode getChatMode();

    boolean enabledChatColors();

    int getSkinParts();

    Hand getMainHand();

    boolean enableTextFiltering();

    boolean allowServerListings();

    enum ChatMode {
        ENABLED,
        COMMANDS,
        HIDDEN
    }

    enum Hand {
        LEFT,
        RIGHT
    }
}
