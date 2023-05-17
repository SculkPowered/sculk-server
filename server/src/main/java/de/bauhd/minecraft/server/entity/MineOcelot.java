package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineOcelot extends AbstractAnimal implements Ocelot {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.OCELOT;
    }
}
