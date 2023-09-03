package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkWitch extends AbstractMob implements Witch {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.WITCH;
    }
}
