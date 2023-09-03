package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkZombieVillager extends SculkZombie implements ZombieVillager {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ZOMBIE_VILLAGER;
    }
}
