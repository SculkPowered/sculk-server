package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public class MineSkeleton extends AbstractMob implements Skeleton {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SKELETON;
    }
}
