package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkAllay extends AbstractEntity implements Allay {
    @Override
    public @NotNull EntityType getType() {
        return EntityType.ALLAY;
    }
}
