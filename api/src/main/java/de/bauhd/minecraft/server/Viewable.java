package de.bauhd.minecraft.server;

import de.bauhd.minecraft.server.entity.player.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface Viewable {

    @NotNull Collection<Player> getViewers();

    void addViewer(@NotNull Player player);

    void removeViewer(@NotNull Player player);

}
