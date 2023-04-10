package de.bauhd.minecraft.server.api.scoreboard;

import net.kyori.adventure.text.Component;

public final class Scoreboard {

    private final Component title;

    public Scoreboard(final Component title) {
        this.title = title;
    }
}
