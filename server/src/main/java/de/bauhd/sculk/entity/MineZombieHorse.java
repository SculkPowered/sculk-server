package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineZombieHorse extends AbstractAnimal implements ZombieHorse {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ZOMBIE_HORSE;
    }
}