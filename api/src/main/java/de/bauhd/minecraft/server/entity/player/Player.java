package de.bauhd.minecraft.server.entity.player;

import de.bauhd.minecraft.server.command.CommandSender;
import de.bauhd.minecraft.server.entity.LivingEntity;
import de.bauhd.minecraft.server.inventory.PlayerInventory;
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

    @NotNull PlayerInventory getInventory();

    int getHeldItemSlot();

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
