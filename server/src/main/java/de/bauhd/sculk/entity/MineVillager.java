package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public class MineVillager extends AbstractAnimal implements Villager {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.VILLAGER;
    }
}