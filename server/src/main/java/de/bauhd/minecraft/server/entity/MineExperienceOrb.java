package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineExperienceOrb extends AbstractEntity implements ExperienceOrb {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.EXPERIENCE_ORB;
    }
}
