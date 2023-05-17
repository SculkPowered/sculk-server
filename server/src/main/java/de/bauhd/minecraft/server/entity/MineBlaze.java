package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineBlaze extends AbstractMob implements Blaze {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.BLAZE;
    }
}
