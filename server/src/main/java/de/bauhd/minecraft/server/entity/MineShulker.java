package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineShulker extends AbstractMob implements Shulker {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SHULKER;
    }
}
