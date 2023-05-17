package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineWitherSkeleton extends AbstractMob implements WitherSkeleton {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.WITHER_SKELETON;
    }
}
