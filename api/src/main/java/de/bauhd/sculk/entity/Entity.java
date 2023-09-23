package de.bauhd.sculk.entity;

import de.bauhd.sculk.Viewable;
import de.bauhd.sculk.world.Position;
import de.bauhd.sculk.world.World;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Represents an entity.
 */
public interface Entity extends Viewable {

    /**
     * @since 1.0.0
     */
    @NotNull UUID getUniqueId();

    /**
     * @since 1.0.0
     */
    int getId();

    /**
     * @since 1.0.0
     */
    @NotNull EntityType getType();

    /**
     * @since 1.0.0
     */
    World getWorld();

    /**
     * @since 1.0.0
     */
    void setWorld(@NotNull World world);

    /**
     * @since 1.0.0
     */
    @NotNull Position getPosition();

    /**
     * @since 1.0.0
     */
    boolean isOnFire();

    /**
     * @since 1.0.0
     */
    boolean isCrouching();

    /**
     * @since 1.0.0
     */
    boolean isSprinting();

    /**
     * @since 1.0.0
     */
    boolean isSwimming();

    /**
     * @since 1.0.0
     */
    boolean isInvisible();

    /**
     * @since 1.0.0
     */
    void setInvisible(boolean invisible);

    /**
     * @since 1.0.0
     */
    boolean isGlowing();

    /**
     * @since 1.0.0
     */
    void setGlowing(boolean glowing);

    /**
     * @since 1.0.0
     */
    @Nullable Component getCustomName();

    /**
     * @since 1.0.0
     */
    void setCustomName(@Nullable Component customName);

    /**
     * @since 1.0.0
     */
    boolean isCustomNameVisible();

    /**
     * @since 1.0.0
     */
    void setCustomNameVisible(boolean visible);

    /**
     * @since 1.0.0
     */
    boolean isSilent();

    /**
     * @since 1.0.0
     */
    void setSilent(boolean silent);

    /**
     * @since 1.0.0
     */
    boolean hasGravity();

    /**
     * @since 1.0.0
     */
    void setGravity(boolean gravity);

    /**
     * @since 1.0.0
     */
    @ApiStatus.Experimental
    @NotNull Pose getPose();

    /**
     * @since 1.0.0
     */
    @ApiStatus.Experimental
    void setPose(@NotNull Pose pose);

    /**
     * Removes the entity.
     *
     * @since 1.0.0
     */
    void remove();

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
        ROARING,
        SNIFFING,
        EMERGIND,
        DIGGING

    }
}
