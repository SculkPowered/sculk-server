package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineBat extends AbstractAnimal implements Bat {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.BAT;
    }
}
