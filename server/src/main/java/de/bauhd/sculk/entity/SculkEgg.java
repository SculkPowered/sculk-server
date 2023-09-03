package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkEgg extends AbstractEntity implements Egg {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.EGG;
    }
}
