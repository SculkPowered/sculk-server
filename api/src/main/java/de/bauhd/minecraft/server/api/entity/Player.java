package de.bauhd.minecraft.server.api.entity;

import net.kyori.adventure.audience.Audience;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface Player extends LivingEntity, Audience {

    @NotNull UUID getUniqueId();

    @NotNull String getUsername();

}
