package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkWitherSkeleton extends AbstractMob implements WitherSkeleton {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.WITHER_SKELETON;
    }
}
