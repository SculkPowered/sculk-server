package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineCaveSpider extends AbstractEntity implements CaveSpider {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.CAVE_SPIDER;
    }
}
