package de.bauhd.minecraft.server.container;

import de.bauhd.minecraft.server.entity.player.MinecraftPlayer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public final class MineBrewingStandContainer extends MineContainer implements BrewingStandContainer {

    private int brewTime;
    private int fuelTime;

    public MineBrewingStandContainer(final Component title) {
        super(title);
    }

    @Override
    public void setBrewTime(int brewTime) {
        this.sendProperty(0, brewTime);
        this.brewTime = brewTime;
    }

    @Override
    public int getBrewTime() {
        return this.brewTime;
    }

    @Override
    public void setFuelTime(@Range(from = 0, to = 20) int fuelTime) {
        this.sendProperty(1, fuelTime);
        this.fuelTime = fuelTime;
    }

    @Override
    public int getFuelTime() {
        return this.fuelTime;
    }

    @Override
    public @NotNull Type getType() {
        return Type.BREWING_STAND;
    }

    @Override
    public void sendProperties(MinecraftPlayer player) {
        player.send(this.property(0, this.brewTime));
        player.send(this.property(1, this.fuelTime));
    }
}
