package eu.sculkpowered.server.team;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface TeamHandler {

  /**
   * Registers a new team by a builder.
   *
   * @param builder the team builder to register
   * @return the new created team
   * @since 1.0.0
   */
  @NotNull Team register(@NotNull Team.Builder builder);

  /**
   * Gets a team by the name.
   *
   * @param name the name of the team
   * @return the team or null
   * @since 1.0.0
   */
  @Nullable Team team(@NotNull String name);

  /**
   * Unregisters a team.
   *
   * @param team the team to unregister
   * @since 1.0.0
   */
  void unregister(@NotNull Team team);
}
