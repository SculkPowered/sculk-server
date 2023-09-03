package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkIronGolem extends AbstractMob implements IronGolem {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.IRON_GOLEM;
    }
}
