package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkAxolotl extends AbstractAnimal implements Axolotl {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.AXOLOTL;
    }
}
