package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkVindicator extends AbstractRaider implements Vindicator {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.VINDICATOR;
    }
}
