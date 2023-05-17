package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineFireball extends AbstractEntity implements Fireball {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.FIREBALL;
    }
}
