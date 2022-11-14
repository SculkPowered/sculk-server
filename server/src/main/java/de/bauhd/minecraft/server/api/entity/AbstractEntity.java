package de.bauhd.minecraft.server.api.entity;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractEntity implements Entity {

    private static final AtomicInteger CURRENT_ID = new AtomicInteger(0);

    private final int id = CURRENT_ID.getAndIncrement();

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public boolean isCrouching() {
        return false;
    }

    @Override
    public boolean isSprinting() {
        return false;
    }

    @Override
    public boolean isSwimming() {
        return false;
    }

    @Override
    public boolean isInvisible() {
        return false;
    }

    @Override
    public void setInvisible(boolean invisible) {

    }

    @Override
    public boolean isGlowing() {
        return false;
    }

    @Override
    public void setGlowing(boolean glowing) {

    }

    @Override
    public @Nullable Component getCustomName() {
        return null;
    }

    @Override
    public void setCustomName(@Nullable Component customName) {

    }

    @Override
    public boolean hasGravity() {
        return false;
    }

    @Override
    public void setGravity(boolean gravity) {

    }

    @Override
    public @NotNull Pose getPose() {
        return null;
    }

    @Override
    public void setPose(@NotNull Pose pose) {

    }
}
