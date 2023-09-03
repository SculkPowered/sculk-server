package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkVindicator extends AbstractMob implements Vindicator {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.VINDICATOR;
    }
}
