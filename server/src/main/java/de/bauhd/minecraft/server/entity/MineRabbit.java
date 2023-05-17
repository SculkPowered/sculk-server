package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineRabbit extends AbstractAnimal implements Rabbit {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.RABBIT;
    }
}
