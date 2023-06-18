package de.bauhd.minecraft.server.container;

import de.bauhd.minecraft.server.entity.player.MinecraftPlayer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class MineLoomContainer extends MineContainer implements LoomContainer {

    private int selectedPattern;

    public MineLoomContainer(Component title) {
        super(title);
    }

    @Override
    public void setSelectedPattern(int pattern) {
        this.sendProperty(0, pattern);
        this.selectedPattern = pattern;
    }

    @Override
    public int getSelectedPattern() {
        return this.selectedPattern;
    }

    @Override
    public @NotNull Type getType() {
        return Type.LOOM;
    }

    @Override
    public void sendProperties(MinecraftPlayer player) {
        player.send(this.property(0, this.selectedPattern));
    }
}
