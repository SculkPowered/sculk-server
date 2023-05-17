package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineDonkey extends AbstractAnimal implements Donkey {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.DONKEY;
    }
}
