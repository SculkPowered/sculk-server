package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkCreeper extends AbstractMob implements Creeper {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.CREEPER;
    }
}
