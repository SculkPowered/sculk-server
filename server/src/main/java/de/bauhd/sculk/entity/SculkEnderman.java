package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkEnderman extends AbstractMob implements Enderman {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ENDERMAN;
    }
}
