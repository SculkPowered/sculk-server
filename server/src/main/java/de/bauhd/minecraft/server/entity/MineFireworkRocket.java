package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineFireworkRocket extends AbstractEntity implements FireworkRocket {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.FIREWORK_ROCKET;
    }
}
