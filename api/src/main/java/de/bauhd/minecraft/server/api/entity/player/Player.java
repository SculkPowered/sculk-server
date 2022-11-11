package de.bauhd.minecraft.server.api.entity.player;

import de.bauhd.minecraft.server.api.command.CommandSender;
import de.bauhd.minecraft.server.api.entity.LivingEntity;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface Player extends LivingEntity, CommandSender {

    @NotNull UUID getUniqueId();

    @NotNull String getUsername();

    @NotNull GameProfile getProfile();

    void disconnect(@NotNull Component component);

}
