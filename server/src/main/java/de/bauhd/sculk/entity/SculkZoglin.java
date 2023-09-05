package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkZoglin extends AbstractMob implements Zoglin {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ZOGLIN;
    }

    @Override
    public boolean isBaby() {
        return this.metadata.getBoolean(17, false);
    }

    @Override
    public void setBaby() {
        this.setBaby(true);
    }

    @Override
    public boolean isAdult() {
        return !this.isBaby();
    }

    @Override
    public void setAdult() {
        this.setBaby(false);
    }

    public void setBaby(boolean baby) {
        this.metadata.setBoolean(17, baby);
    }
}
