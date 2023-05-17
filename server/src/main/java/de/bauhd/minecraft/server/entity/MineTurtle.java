package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineTurtle extends AbstractAnimal implements Turtle {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.TURTLE;
    }
}
