package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineMule extends AbstractAnimal implements Mule {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.MULE;
    }
}
