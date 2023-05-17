package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineSlime extends AbstractMob implements Slime {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SLIME;
    }
}
