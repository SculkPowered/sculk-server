package io.github.sculkpowered.server.command;

import net.kyori.adventure.audience.Audience;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an object that is a source of a command.
 */
public interface CommandSource extends Audience {

  /**
   * Checks if the player has the permission
   *
   * @param permission the permission to test
   * @return whether the player has the permission
   * @since 1.0.0
   */
  boolean hasPermission(@NotNull String permission);
}
