package de.bauhd.minecraft.server.container;

import de.bauhd.minecraft.server.Viewable;
import de.bauhd.minecraft.server.container.item.ItemStack;
import de.bauhd.minecraft.server.entity.player.MinecraftPlayer;
import de.bauhd.minecraft.server.entity.player.Player;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.play.container.ContainerProperty;
import de.bauhd.minecraft.server.util.ItemList;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class MineContainer implements Container, Viewable {

    public final ItemList items = new ItemList(this.getType().size());
    private final Component title;
    private final List<Player> viewers = new ArrayList<>();

    public MineContainer(final Component title) {
        this.title = title;
    }

    @Override
    public @NotNull Component getTitle() {
        return this.title;
    }

    @Override
    public void setItem(int index, @NotNull ItemStack itemStack) {
        this.items.set(index, itemStack);
    }

    @Override
    public @NotNull ItemStack getItem(int index) {
        return this.items.get(index);
    }

    @Override
    public @NotNull Collection<Player> getViewers() {
        return this.viewers;
    }

    @Override
    public void addViewer(@NotNull Player player) {
        this.viewers.add(player);
    }

    @Override
    public void removeViewer(@NotNull Player player) {
        this.viewers.remove(player);
    }

    public Packet property(final int key, final int value) {
        return new ContainerProperty(1, (short) key, (short) value);
    }

    public abstract void sendProperties(MinecraftPlayer player);

    protected void sendProperty(final int key, final int value) {
        final var packet = this.property(key, value);
        for (final var viewer : this.viewers) {
            ((MinecraftPlayer) viewer).send(packet);
        }
    }
}
