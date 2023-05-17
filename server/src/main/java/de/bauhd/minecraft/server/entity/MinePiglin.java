package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MinePiglin extends AbstractMob implements Piglin {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PIGLIN;
    }
}
