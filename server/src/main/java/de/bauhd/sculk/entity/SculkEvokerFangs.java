package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkEvokerFangs extends AbstractEntity implements EvokerFangs {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.EVOKER_FANGS;
    }
}
