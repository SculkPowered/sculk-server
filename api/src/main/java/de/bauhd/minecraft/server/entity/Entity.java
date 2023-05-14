package de.bauhd.minecraft.server.entity;

import de.bauhd.minecraft.server.Viewable;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Entity extends Viewable {

    int getId();

    @NotNull EntityType getType();

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
