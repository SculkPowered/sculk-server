package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineSheep extends AbstractAnimal implements Sheep {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SHEEP;
    }
}
