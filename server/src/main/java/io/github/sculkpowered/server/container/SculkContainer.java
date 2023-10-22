package io.github.sculkpowered.server.container;

import io.github.sculkpowered.server.Viewable;
import io.github.sculkpowered.server.container.item.ItemStack;
import io.github.sculkpowered.server.entity.player.Player;
import io.github.sculkpowered.server.entity.player.SculkPlayer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.play.container.ContainerProperty;
import io.github.sculkpowered.server.util.ItemList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public abstract class SculkContainer implements Container, Viewable {

  public final ItemList items = new ItemList(this.getType().size());
  private final Component title;
  private final List<Player> viewers = new ArrayList<>();

  public SculkContainer(final Component title) {
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
  public boolean addViewer(@NotNull Player player) {
    return this.viewers.add(player);
  }

  @Override
  public boolean removeViewer(@NotNull Player player) {
    return this.viewers.remove(player);
  }

  public Packet property(final int key, final int value) {
    return new ContainerProperty(1, (short) key, (short) value);
  }

  public abstract void sendProperties(SculkPlayer player);

  protected void sendProperty(final int key, final int value) {
    final var packet = this.property(key, value);
    for (final var viewer : this.viewers) {
      ((SculkPlayer) viewer).send(packet);
    }
  }
}
