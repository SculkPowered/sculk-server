package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineEvokerFangs extends AbstractEntity implements EvokerFangs {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.EVOKER_FANGS;
    }
}
