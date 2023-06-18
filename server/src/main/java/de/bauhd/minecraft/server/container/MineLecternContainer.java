package de.bauhd.minecraft.server.container;

import de.bauhd.minecraft.server.entity.player.MinecraftPlayer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class MineLecternContainer extends MineContainer implements LecternContainer {

    private int page;

    public MineLecternContainer(final Component title) {
        super(title);
    }

    @Override
    public void setPage(int page) {
        this.sendProperty(0, page);
        this.page = page;
    }

    @Override
    public int getPage() {
        return this.page;
    }

    @Override
    public @NotNull Type getType() {
        return Type.LECTERN;
    }

    @Override
    public void sendProperties(MinecraftPlayer player) {
        player.send(this.property(0, this.page));
    }
}
