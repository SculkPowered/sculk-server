package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineTropicalFish extends AbstractMob implements TropicalFish {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.TROPICAL_FISH;
    }
}
