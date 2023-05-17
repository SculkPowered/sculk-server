package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineEnderman extends AbstractMob implements Enderman {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ENDERMAN;
    }
}
