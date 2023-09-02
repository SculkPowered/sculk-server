package de.bauhd.sculk.container;

import de.bauhd.sculk.entity.player.MinecraftPlayer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class GenericContainer extends MineContainer {

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
    public void sendProperties(MinecraftPlayer player) {}
}
