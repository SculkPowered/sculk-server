package de.bauhd.minecraft.server.entity;

import de.bauhd.minecraft.server.Viewable;
import de.bauhd.minecraft.server.world.Position;
import de.bauhd.minecraft.server.world.World;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Represents an entity.
 */
public interface Entity extends Viewable {

    @NotNull UUID getUniqueId();

    int getId();

    @NotNull EntityType getType();

    World getWorld();

    void setWorld(@NotNull World world);

    @NotNull Position getPosition();

    boolean isOnFire();

    boolean isCrouching();

    boolean isSprinting();

    boolean isSwimming();

    boolean isInvisible();

    void setInvisible(boolean invisible);

    boolean isGlowing();

    void setGlowing(boolean glowing);

    @Nullable Component getCustomName();

    void setCustomName(@Nullable Component customName);

    boolean isCustomNameVisible();

    void setCustomNameVisible(boolean visible);

    boolean isSilent();

    void setSilent(boolean silent);

    boolean hasGravity();

    void setGravity(boolean gravity);

    @ApiStatus.Experimental
    @NotNull Pose getPose();

    @ApiStatus.Experimental
    void setPose(@NotNull Pose pose);

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
