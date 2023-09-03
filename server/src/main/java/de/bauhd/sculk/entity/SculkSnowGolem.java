package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkSnowGolem extends AbstractMob implements SnowGolem {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SNOW_GOLEM;
    }
}
