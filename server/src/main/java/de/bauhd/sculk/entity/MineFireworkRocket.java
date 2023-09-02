package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineFireworkRocket extends AbstractEntity implements FireworkRocket {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.FIREWORK_ROCKET;
    }
}
