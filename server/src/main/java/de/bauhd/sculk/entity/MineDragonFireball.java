package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineDragonFireball extends AbstractEntity implements DragonFireball {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.FIREBALL;
    }
}
