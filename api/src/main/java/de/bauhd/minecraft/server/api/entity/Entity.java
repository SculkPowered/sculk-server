package de.bauhd.minecraft.server.api.entity;

import org.jetbrains.annotations.NotNull;

public interface Entity {

    int getId();

    @NotNull EntityType getType();

}
