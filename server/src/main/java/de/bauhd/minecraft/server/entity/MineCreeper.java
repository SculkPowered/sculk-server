package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineCreeper extends AbstractMob implements Creeper {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.CREEPER;
    }
}
