package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineGoat extends AbstractAnimal implements Goat {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.GOAT;
    }
}
