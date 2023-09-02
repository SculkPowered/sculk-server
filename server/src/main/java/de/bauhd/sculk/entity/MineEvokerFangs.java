package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineEvokerFangs extends AbstractEntity implements EvokerFangs {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.EVOKER_FANGS;
    }
}
