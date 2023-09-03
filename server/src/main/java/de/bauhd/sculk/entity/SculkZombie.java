package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public class SculkZombie extends AbstractMob implements Zombie {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ZOMBIE;
    }
}
