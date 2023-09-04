package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineExperienceOrb extends AbstractEntity implements ExperienceOrb {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.EXPERIENCE_ORB;
    }
}