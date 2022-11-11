package de.bauhd.minecraft.server.api.entity;

import de.bauhd.minecraft.server.api.command.CommandSender;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface Player extends LivingEntity, CommandSender {

    @NotNull UUID getUniqueId();

    @NotNull String getUsername();

    void disconnect(@NotNull Component component);

}
