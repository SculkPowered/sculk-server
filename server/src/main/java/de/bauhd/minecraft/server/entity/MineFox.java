package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineFox extends AbstractAnimal implements Fox {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.FOX;
    }
}
