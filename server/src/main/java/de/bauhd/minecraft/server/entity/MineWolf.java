package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineWolf extends AbstractTameableAnimal implements Wolf {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.WOLF;
    }
}
