package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineDragonFireball extends AbstractEntity implements DragonFireball {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.FIREBALL;
    }
}
