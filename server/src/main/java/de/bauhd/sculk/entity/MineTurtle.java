package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineTurtle extends AbstractAnimal implements Turtle {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.TURTLE;
    }
}
