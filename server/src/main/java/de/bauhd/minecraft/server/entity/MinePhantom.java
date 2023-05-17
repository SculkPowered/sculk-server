package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MinePhantom extends AbstractMob implements Phantom {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PHANTOM;
    }
}
