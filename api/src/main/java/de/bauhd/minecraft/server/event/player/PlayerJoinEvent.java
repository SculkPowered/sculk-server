package de.bauhd.minecraft.server.event.player;

import de.bauhd.minecraft.server.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public record PlayerJoinEvent(@NotNull Player player) {}
