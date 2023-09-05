package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkWitch extends AbstractRaider implements Witch {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.WITCH;
    }

    @Override
    public boolean isDrinkingPotion() {
        return this.metadata.getBoolean(17, false);
    }

    @Override
    public void setDrinkingPotion(boolean drinkingPotion) {
        this.metadata.setBoolean(17, drinkingPotion);
    }
}
