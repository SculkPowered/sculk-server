package de.bauhd.minecraft.server.entity.player;

import de.bauhd.minecraft.server.command.CommandSender;
import de.bauhd.minecraft.server.connection.Connection;
import de.bauhd.minecraft.server.entity.LivingEntity;
import de.bauhd.minecraft.server.container.Container;
import de.bauhd.minecraft.server.container.Inventory;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Represents a player.
 */
public interface Player extends LivingEntity, CommandSender, PlayerInfoEntry, Connection {

    @NotNull String getUsername();

    @NotNull GameProfile getProfile();

    @NotNull PlayerSettings getSettings();

    @NotNull GameMode getGameMode();

    void setGameMode(@NotNull GameMode gameMode);

    @Nullable Component getDisplayName();

    void setDisplayName(@Nullable Component displayName);

    void disconnect(@NotNull Component component);

    @NotNull Inventory getInventory();

    @Nullable Container getContainer();

    int getHeldItemSlot();

    void openContainer(@NotNull Container container);

    void setHeldItemSlot(int slot);

    boolean isFlying();

    void setFlying(boolean flying);

    boolean isAllowFlight();

    void setAllowFight(boolean allowFight);

    float getFlyingSpeed();

    void setFlyingSpeed(float flyingSpeed);

    boolean canInstantBreak();

    void setInstantBreak(boolean instantBreak);

    float getViewModifier();

    void setViewModifier(float viewModifier);
}
