package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineExperienceBottle extends AbstractEntity implements ExperienceBottle {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.EXPERIENCE_BOTTLE;
    }
}
