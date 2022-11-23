package de.bauhd.minecraft.server.api.entity.player;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface PlayerInfoEntry {

    @NotNull GameProfile getProfile();

    @NotNull GameMode getGameMode();

    @Nullable Component getDisplayName();

    int getPing();

}
