package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class AbstractTameableAnimal extends AbstractAnimal implements Tameable {

    @Override
    public boolean isSitting() {
        return this.metadata.inMask(17, 0x01);
    }

    @Override
    public void setSitting(boolean sitting) {
        this.metadata.setMask(17, 0x01, sitting);
    }

    @Override
    public boolean isTamed() {
        return this.metadata.inMask(17, 0x04);
    }

    @Override
    public @Nullable UUID getOwner() {
        return this.metadata.getOptUUID(18, null);
    }
}
