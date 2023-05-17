package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineVindicator extends AbstractMob implements Vindicator {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.VINDICATOR;
    }
}
