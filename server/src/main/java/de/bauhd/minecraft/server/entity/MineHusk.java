package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineHusk extends MineZombie implements Husk {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.HUSK;
    }
}
