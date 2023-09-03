package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkEvoker extends AbstractMob implements Evoker {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.EVOKER;
    }
}
