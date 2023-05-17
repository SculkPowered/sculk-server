package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineSniffer extends AbstractAnimal implements Sniffer {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SNIFFER;
    }
}
