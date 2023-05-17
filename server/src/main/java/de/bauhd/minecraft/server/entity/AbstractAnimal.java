package de.bauhd.minecraft.server.entity;

public abstract class AbstractAnimal extends AbstractMob implements Animal {

    @Override
    public boolean isBaby() {
        return this.metadata.getBoolean(16, false);
    }

    @Override
    public void setBaby(boolean baby) {
        this.metadata.setBoolean(16, baby);
    }
}
