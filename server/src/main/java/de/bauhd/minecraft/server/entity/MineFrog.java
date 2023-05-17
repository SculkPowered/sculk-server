package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineFrog extends AbstractAnimal implements Frog {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.FROG;
    }
}
