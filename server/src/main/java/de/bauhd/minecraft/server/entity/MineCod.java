package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineCod extends AbstractAnimal implements Cod {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.COD;
    }
}
