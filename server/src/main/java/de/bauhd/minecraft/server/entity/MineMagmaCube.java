package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineMagmaCube extends AbstractMob implements MagmaCube {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.MAGMA_CUBE;
    }
}
