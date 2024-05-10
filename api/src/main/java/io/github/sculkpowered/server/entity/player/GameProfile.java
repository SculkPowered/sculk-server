package io.github.sculkpowered.server.entity.player;

import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a profile of a player.
 */
public record GameProfile(
    UUID uniqueId,
    String name,
    @NotNull List<Property> properties
) {

  /**
   * Represents a property of a profile.
   *
   * @param key       the key of the property
   * @param value     the value of the property
   * @param signature the signature of the property
   * @since 1.0.0
   */
  public record Property(
      @NotNull String key,
      @NotNull String value,
      @Nullable String signature
  ) {

  }
}
