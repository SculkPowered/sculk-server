package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineAxolotl extends AbstractAnimal implements Axolotl {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.AXOLOTL;
    }
}
