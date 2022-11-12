package de.bauhd.minecraft.server.api.entity.player;

import de.bauhd.minecraft.server.api.command.CommandSender;
import de.bauhd.minecraft.server.api.entity.LivingEntity;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface Player extends LivingEntity, CommandSender {

    @NotNull UUID getUniqueId();

    @NotNull String getUsername();

    @NotNull GameProfile getProfile();

    @NotNull GameMode getGameMode();

    void setGameMode(@NotNull GameMode gameMode);

    @Nullable Component getDisplayName();

    void setDisplayName(@Nullable Component displayName);

    void disconnect(@NotNull Component component);

}
