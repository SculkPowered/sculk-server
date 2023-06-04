package de.bauhd.minecraft.server.container;

import de.bauhd.minecraft.server.entity.player.MinecraftPlayer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class MineFurnaceContainer extends MineContainer implements FurnaceContainer {

    private int fuelLeft;
    private int maxFuelBurnTime;
    private int progressArrow;
    private int maximumProgress;

    public MineFurnaceContainer(final Component title) {
        super(title);
    }

    @Override
    public void setFuelLeft(int fuelLeft) {
        this.sendProperty(0, fuelLeft);
        this.fuelLeft = fuelLeft;
    }

    @Override
    public int getFuelLeft() {
        return this.fuelLeft;
    }

    @Override
    public void setMaxFuelBurnTime(int maxFuelBurnTime) {
        this.sendProperty(1, maxFuelBurnTime);
        this.maxFuelBurnTime = maxFuelBurnTime;
    }

    @Override
    public int getMaxFuelBurnTime() {
        return this.maxFuelBurnTime;
    }

    @Override
    public void setProgressArrow(int progressArrow) {
        this.sendProperty(2, progressArrow);
        this.progressArrow = progressArrow;
    }

    @Override
    public int getProgressArrow() {
        return this.progressArrow;
    }

    @Override
    public void setMaximumProgress(int maximumProgress) {
        this.sendProperty(3, maximumProgress);
        this.maximumProgress = maximumProgress;
    }

    @Override
    public int getMaximumProgress() {
        return this.maximumProgress;
    }

    @Override
    public @NotNull Type type() {
        return Type.FURNACE;
    }

    @Override
    public void sendProperties(MinecraftPlayer player) {
        player.send(this.property(0, this.fuelLeft));
        player.send(this.property(1, this.maxFuelBurnTime));
        player.send(this.property(2, this.progressArrow));
        player.send(this.property(3, this.maximumProgress));
    }
}
