package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineDrowned extends AbstractMob implements Drowned {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.DROWNED;
    }
}