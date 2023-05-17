package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineHorse extends AbstractAnimal implements Horse {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.HORSE;
    }
}
