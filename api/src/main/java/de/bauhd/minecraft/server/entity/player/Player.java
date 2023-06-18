package de.bauhd.minecraft.server.entity.player;

import de.bauhd.minecraft.server.command.CommandSender;
import de.bauhd.minecraft.server.connection.Connection;
import de.bauhd.minecraft.server.entity.LivingEntity;
import de.bauhd.minecraft.server.container.Container;
import de.bauhd.minecraft.server.container.Inventory;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a player.
 */
public interface Player extends LivingEntity, CommandSender, PlayerInfoEntry, Connection {

    /**
     * Gets the name of the player.
     * @return the player's name
     */
    @NotNull String getUsername();

    /**
     * Gets the profile of the player.
     * @return the player's profile
     */
    @NotNull GameProfile getProfile();

    /**
     * Gets the settings of the player.
     * @return the player's settings
     */
    @NotNull PlayerSettings getSettings();

    /**
     * Gets the game mode of the player.
     * @return the player's game mode
     */
    @NotNull GameMode getGameMode();

    /**
     * Sets the game mode of a player.
     * @param gameMode the game mode to set
     */
    void setGameMode(@NotNull GameMode gameMode);

    /**
     * Gets the game mode of the player.
     * @return the player's game mode
     */
    @Nullable Component getDisplayName();

    /**
     * Sets the display name of a player.
     * @param displayName the display name to set
     */
    void setDisplayName(@Nullable Component displayName);

    /**
     * Disconnects the player with the specified reason.
     * @param reason the reason of the disconnect
     */
    void disconnect(@NotNull Component reason);

    /**
     * Gets the inventory of the player.
     * @return the player's inventory
     */
    @NotNull Inventory getInventory();

    /**
     * Gets the opened container.
     * @return the opened container or {@code null} if nothing is opened
     */
    @Nullable Container getOpenedContainer();

    /**
     * Opens the specified container.
     * @param container the container to open
     */
    void openContainer(@NotNull Container container);

    /**
     * Gets the slot index of the currently held item
     * @return the held item slot index
     */
    int getHeldItemSlot();

    /**
     * Sets the current held item by its index.
     * @param slot the slot
     */
    void setHeldItemSlot(int slot);

    /**
     * Gets whether the player flies or not.
     * @return whether the player flies or not
     */
    boolean isFlying();

    /**
     * Sets whether the player flies or not.
     * @param flying whether the player flies or not
     */
    void setFlying(boolean flying);

    /**
     * Gets whether the player is allowed to fly.
     * @return whether the player is allowed to fly
     */
    boolean isAllowFlight();

    /**
     * Sets whether the player is allowed to fly
     * @param allowFight whether the player is allowed to fly
     */
    void setAllowFight(boolean allowFight);

    /**
     * Gets the flying speed of the player.
     * @return the player's flying speed
     */
    float getFlyingSpeed();

    /**
     *
     * @param flyingSpeed
     */
    void setFlyingSpeed(float flyingSpeed);

    /**
     * Gets whether the player can instantly break.
     * @return whether the player can instantly break
     */
    @ApiStatus.Experimental
    boolean canInstantBreak();

    /**
     * Sets whether the player can instantly break.
     * @param instantBreak whether the player can instantly break
     */
    @ApiStatus.Experimental
    void setInstantBreak(boolean instantBreak);

    /**
     * Gets the view modifier of the player.
     * @return the player's view modifier
     */
    float getViewModifier();

    /**
     * Sets the view modifier of the player.
     * @param viewModifier the view modifier to set
     */
    void setViewModifier(float viewModifier);
}
