package de.bauhd.minecraft.server.entity.player;

import de.bauhd.minecraft.server.command.CommandSender;
import de.bauhd.minecraft.server.entity.LivingEntity;
import de.bauhd.minecraft.server.world.World;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface Player extends LivingEntity, CommandSender, PlayerInfoEntry {

    @NotNull UUID getUniqueId();

    @NotNull String getUsername();

    @NotNull GameProfile getProfile();

    @NotNull GameMode getGameMode();

    void setGameMode(@NotNull GameMode gameMode);

    @Nullable Component getDisplayName();

    void setDisplayName(@Nullable Component displayName);

    void disconnect(@NotNull Component component);

    int getHeldItemSlot();

    void setHeldItemSlot(int slot);

    World getWorld();

    void setWorld(@NotNull World world);
}
