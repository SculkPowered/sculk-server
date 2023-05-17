package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineSkeletonHorse extends AbstractAnimal implements SkeletonHorse {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SKELETON_HORSE;
    }
}
