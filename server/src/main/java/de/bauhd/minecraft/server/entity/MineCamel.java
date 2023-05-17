package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineCamel extends AbstractAnimal implements Camel {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.CAMEL;
    }
}
