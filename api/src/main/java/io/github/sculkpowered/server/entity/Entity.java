package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.Viewable;
import io.github.sculkpowered.server.world.Position;
import io.github.sculkpowered.server.world.Vector;
import io.github.sculkpowered.server.world.World;
import java.util.UUID;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.ApiStatus.Experimental;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an entity.
 */
public interface Entity extends Viewable, Sound.Emitter {

  /**
   * @since 1.0.0
   */
  @NotNull UUID uniqueId();

  /**
   * @since 1.0.0
   */
  int id();

  /**
   * @since 1.0.0
   */
  @NotNull EntityType type();

  /**
   * @since 1.0.0
   */
  World world();

  /**
   * @since 1.0.0
   */
  void world(@NotNull World world);

  /**
   * @since 1.0.0
   */
  @NotNull Position position();

  /**
   * @since 1.0.0
   */
  @Experimental
  void velocity(@NotNull Vector vector);

  /**
   * @since 1.0.0
   */
  @Experimental
  @NotNull Vector velocity();

  /**
   * @since 1.0.0
   */
  boolean onFire();

  /**
   * @since 1.0.0
   */
  boolean crouching();

  /**
   * @since 1.0.0
   */
  boolean sprinting();

  /**
   * @since 1.0.0
   */
  boolean swimming();

  /**
   * @since 1.0.0
   */
  boolean invisible();

  /**
   * @since 1.0.0
   */
  void invisible(boolean invisible);

  /**
   * @since 1.0.0
   */
  boolean glowing();

  /**
   * @since 1.0.0
   */
  void glowing(boolean glowing);

  /**
   * @since 1.0.0
   */
  @Nullable Component customName();

  /**
   * @since 1.0.0
   */
  void customName(@Nullable Component customName);

  /**
   * @since 1.0.0
   */
  boolean customNameVisible();

  /**
   * @since 1.0.0
   */
  void customNameVisible(boolean visible);

  /**
   * @since 1.0.0
   */
  boolean silent();

  /**
   * @since 1.0.0
   */
  void silent(boolean silent);

  /**
   * @since 1.0.0
   */
  boolean hasGravity();

  /**
   * @since 1.0.0
   */
  void gravity(boolean gravity);

  /**
   * Teleports an entity to the specified position.
   *
   * @param position the position to teleport
   * @since 1.0.0
   */
  void teleport(@NotNull Position position);

  /**
   * @since 1.0.0
   */
  @ApiStatus.Experimental
  @NotNull Pose pose();

  /**
   * @since 1.0.0
   */
  @ApiStatus.Experimental
  void pose(@NotNull Pose pose);

  @ApiStatus.Experimental
  enum Pose {

    STANDING,
    FALL_FLYING,
    SLEEPING,
    SWIMMING,
    SPIN_ATTACK,
    SNEAKING,
    LONG_JUMPING,
    DYING,
    CROAKING,
    USING_TONGUE,
    SITTING,
    ROARING,
    SNIFFING,
    EMERGING,
    DIGGING,
    SLIDING,
    SHOOTING,
    INHALING
  }
}
