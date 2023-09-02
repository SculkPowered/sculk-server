package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineFireball extends AbstractEntity implements Fireball {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.FIREBALL;
    }
}
