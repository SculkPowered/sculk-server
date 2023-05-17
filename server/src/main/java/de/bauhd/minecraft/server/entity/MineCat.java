package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineCat extends AbstractTameableAnimal implements Cat {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.CAT;
    }
}
