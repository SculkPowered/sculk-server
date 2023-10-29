package io.github.sculkpowered.server.entity.player;

import io.github.sculkpowered.server.command.CommandSource;
import io.github.sculkpowered.server.connection.Connection;
import io.github.sculkpowered.server.container.Container;
import io.github.sculkpowered.server.container.Inventory;
import io.github.sculkpowered.server.entity.LivingEntity;
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
  @NotNull String name();

  /**
   * Gets the profile of the player.
   *
   * @return the player's profile
   * @since 1.0.0
   */
  @NotNull GameProfile profile();

  /**
   * Gets the settings of the player.
   *
   * @return the player's settings
   * @since 1.0.0
   */
  @NotNull PlayerSettings settings();

  /**
   * Gets the game mode of the player.
   *
   * @return the player's game mode
   * @since 1.0.0
   */
  @NotNull GameMode gameMode();

  /**
   * Sets the game mode of a player.
   *
   * @param gameMode the game mode to set
   * @since 1.0.0
   */
  void gameMode(@NotNull GameMode gameMode);

  /**
   * Gets the game mode of the player.
   *
   * @return the player's game mode
   * @since 1.0.0
   */
  @Nullable Component displayName();

  /**
   * Sets the display name of a player.
   *
   * @param displayName the display name to set
   * @since 1.0.0
   */
  void displayName(@Nullable Component displayName);

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
  @NotNull Inventory inventory();

  /**
   * Gets the opened container.
   *
   * @return the opened container or {@code null} if nothing is opened
   * @since 1.0.0
   */
  @Nullable Container openedContainer();

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
  int heldItemSlot();

  /**
   * Sets the current held item by its index.
   *
   * @param slot the slot
   * @since 1.0.0
   */
  void heldItemSlot(int slot);

  /**
   * Gets whether the player flies or not.
   *
   * @return whether the player flies or not
   * @since 1.0.0
   */
  boolean flying();

  /**
   * Sets whether the player flies or not.
   *
   * @param flying whether the player flies or not
   * @since 1.0.0
   */
  void flying(boolean flying);

  /**
   * Gets whether the player is allowed to fly.
   *
   * @return whether the player is allowed to fly
   * @since 1.0.0
   */
  boolean allowFlight();

  /**
   * Sets whether the player is allowed to fly
   *
   * @param allowFight whether the player is allowed to fly
   * @since 1.0.0
   */
  void allowFlight(boolean allowFight);

  /**
   * Gets the flying speed of the player.
   *
   * @return the player's flying speed
   * @since 1.0.0
   */
  float flyingSpeed();

  /**
   * Sets the flying speed of the player
   *
   * @param flyingSpeed the flying speed to set
   * @since 1.0.0
   */
  void flyingSpeed(float flyingSpeed);

  /**
   * Gets whether the player can instantly break.
   *
   * @return whether the player can instantly break
   * @since 1.0.0
   */
  @ApiStatus.Experimental
  boolean instantBreak();

  /**
   * Sets whether the player can instantly break.
   *
   * @param instantBreak whether the player can instantly break
   * @since 1.0.0
   */
  @ApiStatus.Experimental
  void instantBreak(boolean instantBreak);

  /**
   * Gets the view modifier of the player.
   *
   * @return the player's view modifier
   * @since 1.0.0
   */
  float viewModifier();

  /**
   * Sets the view modifier of the player.
   *
   * @param viewModifier the view modifier to set
   * @since 1.0.0
   */
  void viewModifier(float viewModifier);

  /**
   * Sends a plugin message.
   *
   * @param identifier the identifier
   * @param data       the data to send
   */
  void sendPluginMessage(@NotNull String identifier, byte @NotNull [] data);
}
