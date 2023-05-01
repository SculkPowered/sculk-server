package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface Tameable {

    boolean isSitting();

    void setSitting(boolean sitting);

    boolean isTamed();

    @Nullable UUID getOwner();

}
