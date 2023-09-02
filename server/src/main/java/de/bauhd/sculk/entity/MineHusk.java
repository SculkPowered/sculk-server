package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineHusk extends MineZombie implements Husk {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.HUSK;
    }
}
