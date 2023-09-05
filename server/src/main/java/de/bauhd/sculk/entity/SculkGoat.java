package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkGoat extends AbstractAnimal implements Goat {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.GOAT;
    }

    @Override
    public boolean isScreamingGoat() {
        return this.metadata.getBoolean(17, false);
    }

    @Override
    public void setScreamingGoat(boolean screamingGoat) {
        this.metadata.setBoolean(17, screamingGoat);
    }

    @Override
    public boolean hasLeftHorn() {
        return this.metadata.getBoolean(18, true);
    }

    @Override
    public void setLeftHorn(boolean leftHorn) {
        this.metadata.setBoolean(18, leftHorn);
    }

    @Override
    public boolean hasRightHorn() {
        return this.metadata.getBoolean(19, true);
    }

    @Override
    public void setRightHorn(boolean rightHorn) {
        this.metadata.setBoolean(19, rightHorn);
    }
}
