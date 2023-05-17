package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineHoglin extends AbstractAnimal implements Hoglin {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.HOGLIN;
    }
}
