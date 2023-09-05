package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkBee extends AbstractAnimal implements Bee {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.BEE;
    }

    @Override
    public boolean isAngry() {
        return this.metadata.inMask(17, 0x02);
    }

    @Override
    public void setAngry(boolean angry) {
        this.metadata.setMask(17, 0x02, angry);
    }

    @Override
    public boolean hasStung() {
        return this.metadata.inMask(17, 0x04);
    }

    @Override
    public void setStung(boolean stung) {
        this.metadata.setMask(17, 0x04, stung);
    }

    @Override
    public boolean hasNectar() {
        return this.metadata.inMask(17, 0x08);
    }

    @Override
    public void setNectar(boolean nectar) {
        this.metadata.setMask(17, 0x08, nectar);
    }
}
