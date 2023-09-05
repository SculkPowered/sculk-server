package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkEvoker extends AbstractRaider implements Evoker {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.EVOKER;
    }
}
