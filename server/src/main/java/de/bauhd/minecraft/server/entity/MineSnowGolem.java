package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineSnowGolem extends AbstractMob implements SnowGolem {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SNOW_GOLEM;
    }
}
