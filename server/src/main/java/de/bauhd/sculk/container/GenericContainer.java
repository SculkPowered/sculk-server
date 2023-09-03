package de.bauhd.sculk.container;

import de.bauhd.sculk.entity.player.SculkPlayer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class GenericContainer extends SculkContainer {

    private final Type type;

    public GenericContainer(final Type type, final Component title) {
        super(title);
        this.type = type;
    }

    @Override
    public @NotNull Type getType() {
        return this.type;
    }

    @Override
    public void sendProperties(SculkPlayer player) {}
}
