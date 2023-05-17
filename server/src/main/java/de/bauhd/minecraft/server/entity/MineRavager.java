package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineRavager extends AbstractMob implements Ravager {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.RAVAGER;
    }
}
