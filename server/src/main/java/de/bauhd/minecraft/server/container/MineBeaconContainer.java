package de.bauhd.minecraft.server.container;

import de.bauhd.minecraft.server.entity.player.MinecraftPlayer;
import de.bauhd.minecraft.server.potion.PotionEffect;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public final class MineBeaconContainer extends MineContainer implements BeaconContainer {

    private int powerLevel;
    private PotionEffect firstPotionEffect;
    private PotionEffect secondPotionEffect;

    public MineBeaconContainer(final Component title) {
        super(title);
    }

    @Override
    public void setPowerLevel(@Range(from = 0, to = 4) int powerLevel) {
        this.sendProperty(0, powerLevel);
        this.powerLevel = powerLevel;
    }

    @Override
    public int getPowerLevel() {
        return this.powerLevel;
    }

    @Override
    public void setFirstPotionEffect(@NotNull PotionEffect potionEffect) {
        this.sendProperty(1, potionEffect.protocolId());
        this.firstPotionEffect = potionEffect;
    }

    @Override
    public @NotNull PotionEffect getFirstPotionEffect() {
        return this.firstPotionEffect;
    }

    @Override
    public void setSecondPotionEffect(@NotNull PotionEffect potionEffect) {
        this.sendProperty(2, potionEffect.protocolId());
        this.secondPotionEffect = potionEffect;
    }

    @Override
    public @NotNull PotionEffect getSecondPotionEffect() {
        return this.secondPotionEffect;
    }

    @Override
    public @NotNull Type type() {
        return Type.BEACON;
    }

    @Override
    public void sendProperties(MinecraftPlayer player) {
        player.send(this.property(0, this.powerLevel));
        player.send(this.property(1, this.firstPotionEffect.protocolId()));
        player.send(this.property(2, this.secondPotionEffect.protocolId()));
    }
}
