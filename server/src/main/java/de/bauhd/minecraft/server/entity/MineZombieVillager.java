package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineZombieVillager extends MineZombie implements ZombieVillager {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ZOMBIE_VILLAGER;
    }
}
