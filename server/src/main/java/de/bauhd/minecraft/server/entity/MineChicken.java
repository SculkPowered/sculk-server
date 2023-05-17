package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineChicken extends AbstractAnimal implements Chicken {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.CHICKEN;
    }
}
