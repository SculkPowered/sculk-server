package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineSmallFireball extends AbstractEntity implements SmallFireball {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SMALL_FIREBALL;
    }
}
