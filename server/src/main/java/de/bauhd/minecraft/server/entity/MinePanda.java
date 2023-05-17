package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MinePanda extends AbstractAnimal implements Panda {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PANDA;
    }
}
