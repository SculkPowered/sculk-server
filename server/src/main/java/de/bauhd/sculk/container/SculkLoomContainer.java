package de.bauhd.sculk.container;

import de.bauhd.sculk.entity.player.SculkPlayer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class SculkLoomContainer extends SculkContainer implements LoomContainer {

    private int selectedPattern;

    public SculkLoomContainer(Component title) {
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
    public void sendProperties(SculkPlayer player) {
        player.send(this.property(0, this.selectedPattern));
    }
}
