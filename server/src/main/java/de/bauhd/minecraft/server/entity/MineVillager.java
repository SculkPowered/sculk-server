package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public class MineVillager extends AbstractAnimal implements Villager {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.VILLAGER;
    }
}
