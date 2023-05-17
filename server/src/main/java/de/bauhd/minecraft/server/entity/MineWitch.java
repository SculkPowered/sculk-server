package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineWitch extends AbstractMob implements Witch {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.WITCH;
    }
}
