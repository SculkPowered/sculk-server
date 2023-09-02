package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineWanderingTrader extends AbstractMob implements WanderingTrader {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.WANDERING_TRADER;
    }
}
