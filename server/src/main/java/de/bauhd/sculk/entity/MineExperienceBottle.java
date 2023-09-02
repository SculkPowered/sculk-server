package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineExperienceBottle extends AbstractEntity implements ExperienceBottle {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.EXPERIENCE_BOTTLE;
    }
}
