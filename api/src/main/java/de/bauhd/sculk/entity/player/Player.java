package de.bauhd.sculk.entity.player;

import de.bauhd.sculk.command.CommandSource;
import de.bauhd.sculk.connection.Connection;
import de.bauhd.sculk.container.Container;
import de.bauhd.sculk.container.Inventory;
import de.bauhd.sculk.entity.LivingEntity;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a player.
 */
public interface Player extends LivingEntity, CommandSource, PlayerInfoEntry, Connection {

    /**
     * Gets the name of the player.
     *
     * @return the player's name
     * @since 1.0.0
     */
    @NotNull String getUsername();

    /**
     * Gets the profile of the player.
     *
     * @return the player's profile
     * @since 1.0.0
     */
    @NotNull GameProfile getProfile();

    /**
     * Gets the settings of the player.
     *
     * @return the player's settings
     * @since 1.0.0
     */
    @NotNull PlayerSettings getSettings();

    /**
     * Gets the game mode of the player.
     *
     * @return the player's game mode
     * @since 1.0.0
     */
    @NotNull GameMode getGameMode();

    /**
     * Sets the game mode of a player.
     *
     * @param gameMode the game mode to set
     * @since 1.0.0
     */
    void setGameMode(@NotNull GameMode gameMode);

    /**
     * Gets the game mode of the player.
     *
     * @return the player's game mode
     * @since 1.0.0
     */
    @Nullable Component getDisplayName();

    /**
     * Sets the display name of a player.
     *
     * @param displayName the display name to set
     * @since 1.0.0
     */
    void setDisplayName(@Nullable Component displayName);

    /**
     * Disconnects the player with the specified reason.
     *
     * @param reason the reason of the disconnect
     * @since 1.0.0
     */
    void disconnect(@NotNull Component reason);

    /**
     * Gets the inventory of the player.
     *
     * @return the player's inventory
     * @since 1.0.0
     */
    @NotNull Inventory getInventory();

    /**
     * Gets the opened container.
     *
     * @return the opened container or {@code null} if nothing is opened
     * @since 1.0.0
     */
    @Nullable Container getOpenedContainer();

    /**
     * Opens the specified container.
     *
     * @param container the container to open
     * @since 1.0.0
     */
    void openContainer(@NotNull Container container);

    /**
     * Gets the slot index of the currently held item
     *
     * @return the held item slot index
     * @since 1.0.0
     */
    int getHeldItemSlot();

    /**
     * Sets the current held item by its index.
     *
     * @param slot the slot
     * @since 1.0.0
     */
    void setHeldItemSlot(int slot);

    /**
     * Gets whether the player flies or not.
     *
     * @return whether the player flies or not
     * @since 1.0.0
     */
    boolean isFlying();

    /**
     * Sets whether the player flies or not.
     *
     * @param flying whether the player flies or not
     * @since 1.0.0
     */
    void setFlying(boolean flying);

    /**
     * Gets whether the player is allowed to fly.
     *
     * @return whether the player is allowed to fly
     * @since 1.0.0
     */
    boolean isAllowFlight();

    /**
     * Sets whether the player is allowed to fly
     *
     * @param allowFight whether the player is allowed to fly
     * @since 1.0.0
     */
    void setAllowFight(boolean allowFight);

    /**
     * Gets the flying speed of the player.
     *
     * @return the player's flying speed
     * @since 1.0.0
     */
    float getFlyingSpeed();

    /**
     * Sets the flying speed of the player
     *
     * @param flyingSpeed the flying speed to set
     * @since 1.0.0
     */
    void setFlyingSpeed(float flyingSpeed);

    /**
     * Gets whether the player can instantly break.
     *
     * @return whether the player can instantly break
     * @since 1.0.0
     */
    @ApiStatus.Experimental
    boolean canInstantBreak();

    /**
     * Sets whether the player can instantly break.
     *
     * @param instantBreak whether the player can instantly break
     * @since 1.0.0
     */
    @ApiStatus.Experimental
    void setInstantBreak(boolean instantBreak);

    /**
     * Gets the view modifier of the player.
     *
     * @return the player's view modifier
     * @since 1.0.0
     */
    float getViewModifier();

    /**
     * Sets the view modifier of the player.
     *
     * @param viewModifier the view modifier to set
     * @since 1.0.0
     */
    void setViewModifier(float viewModifier);
}
