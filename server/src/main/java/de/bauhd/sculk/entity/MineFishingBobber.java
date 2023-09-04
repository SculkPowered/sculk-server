package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineFishingBobber extends AbstractEntity implements FishingBobber {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.FISHING_BOBBER;
    }
}